package com.example.local.dataStore

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject
import androidx.core.content.edit

/*
* SecureTokenLocalService.kt
* This file is part of the ComposeBase project.
* Created by HungNguyenMinh on 18/6/2025.
* This service provides secure storage and retrieval of access and refresh tokens using Android's Keystore system.
* */
class SecureTokenLocalService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFS_NAME = "secure_token_prefs"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_IV_ACCESS = "access_token_iv"
        private const val KEY_IV_REFRESH = "refresh_token_iv"
        private const val KEY_ALIAS = "token_key"
        private const val PROVIDER = "AndroidKeyStore"
        private const val TRANSFORMATION = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        generateKeyIfNotExists()
    }

    private fun generateKeyIfNotExists() {
        try {
            val keyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER)
                keyGenerator.init(
                    KeyGenParameterSpec.Builder(
                        KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    )
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .setUserAuthenticationRequired(false)
                        .build()
                )
                keyGenerator.generateKey()
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to generate key", e)
        }
    }

    private fun encryptToken(token: String): Pair<String, String>? {
        return try {
            val keyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }
            val secretKey = keyStore.getKey(KEY_ALIAS, null) as? SecretKey ?: return null
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val encryptedBytes = cipher.doFinal(token.toByteArray(Charsets.UTF_8))
            Pair(
                Base64.encodeToString(encryptedBytes, Base64.DEFAULT),
                Base64.encodeToString(cipher.iv, Base64.DEFAULT)
            )
        } catch (_: Exception) {
            null
        }
    }

    private fun decryptToken(encryptedToken: String, iv: String): String? {
        return try {
            val keyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }
            val secretKey = keyStore.getKey(KEY_ALIAS, null) as? SecretKey ?: return null
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val ivBytes = Base64.decode(iv, Base64.DEFAULT)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(ivBytes))
            val decryptedBytes = cipher.doFinal(Base64.decode(encryptedToken, Base64.DEFAULT))
            String(decryptedBytes, Charsets.UTF_8)
        } catch (_: Exception) {
            null
        }
    }

    suspend fun saveToken(accessToken: String) = withContext(Dispatchers.IO) {
        val encryptedPair = encryptToken(accessToken) ?: throw RuntimeException("Encryption failed")
        sharedPreferences.edit {
            putString(KEY_ACCESS_TOKEN, encryptedPair.first)
                .putString(KEY_IV_ACCESS, encryptedPair.second)
        }
    }

    fun getAccessTokenSync(): String? {
        val encryptedToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        val iv = sharedPreferences.getString(KEY_IV_ACCESS, null)
        return if (encryptedToken != null && iv != null) {
            decryptToken(encryptedToken, iv)
        } else {
            null
        }
    }

    suspend fun getAccessToken(): String? = withContext(Dispatchers.IO) {
        getAccessTokenSync()
    }

    suspend fun saveRefreshToken(refreshToken: String) = withContext(Dispatchers.IO) {
        val encryptedPair = encryptToken(refreshToken) ?: throw RuntimeException("Encryption failed")
        sharedPreferences.edit {
            putString(KEY_REFRESH_TOKEN, encryptedPair.first)
                .putString(KEY_IV_REFRESH, encryptedPair.second)
        }
    }

    fun getRefreshTokenSync(): String? {
        val encryptedToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        val iv = sharedPreferences.getString(KEY_IV_REFRESH, null)
        return if (encryptedToken != null && iv != null) {
            decryptToken(encryptedToken, iv)
        } else {
            null
        }
    }

    suspend fun getRefreshToken(): String? = withContext(Dispatchers.IO) {
        getRefreshTokenSync()
    }
}