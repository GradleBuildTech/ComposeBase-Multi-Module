package com.example.local.dataStore

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec

class SecureTokenLocalService(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)
    // Constants for token keys and Android Keystore
    private val keyAlias = "token_key"
    // Android Keystore provider and transformation
    // This is a placeholder; actual implementation would require using KeyGenerator
    private val provider = "AndroidKeyStore"

    private val keyAccessToken = "access_token"

    private val ivAccessToken = "iv_access_token"

    private val keyRefreshToken = "refresh_token"

    private val ivRefreshToken = "iv_refresh_token"

    private val transform = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"

    init {
        generateKeyIfNotExist()
    }


    private fun generateKeyIfNotExist() {
        // Implementation to generate a key in the Android Keystore
        // This is a placeholder; actual implementation would require using KeyGenerator
        try {
            val keyStore = KeyStore.getInstance(provider)
            keyStore.load(null)
            if (!keyStore.containsAlias(keyAlias)) {
                val keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, provider // Init  aes algorithm with Android Keystore to encrypt/decrypt tokens
                )
                keyGenerator.init(
                    KeyGenParameterSpec.Builder(
                        keyAlias,
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
            e.printStackTrace()
            // Handle key generation error
            throw RuntimeException("Failed to generate key for secure token storage", e)
        }
    }

    // Function to set the access token securely
    private fun encryptToken(token: String): Pair<String, String>? {
        try {
            val keyStore = KeyStore.getInstance(provider)
            keyStore.load(null)
            val secretKey = keyStore.getKey(keyAlias, null) ?: return null

            // Use Cipher to encrypt the token
            val cipher = Cipher.getInstance(transform)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val encryptedToken = cipher.doFinal(token.toByteArray(Charsets.UTF_8))
            // Store the encrypted token and IV in SharedPreferences
            return Pair(
                Base64.encodeToString(encryptedToken, Base64.DEFAULT),
                Base64.encodeToString(cipher.iv, Base64.DEFAULT)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    // Function to get the access token securely
    private fun decryptToken(encryptedToken: String, iv: String): String? {
        return try {
            val keyStore = KeyStore.getInstance(provider).apply { load(null) }
            val secretKey = keyStore.getKey(keyAlias, null) ?: return null

            // Use Cipher to decrypt the token
            val cipher = Cipher.getInstance(transform)
            val ivBytes = Base64.decode(iv, Base64.DEFAULT)
            cipher.init(Cipher.DECRYPT_MODE, secretKey,IvParameterSpec(ivBytes))
            val decryptedToken = cipher.doFinal(Base64.decode(encryptedToken, Base64.DEFAULT))
            String(decryptedToken, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //Save token
    suspend fun saveToken(accessToken: String, refreshToken: String): SharedPreferences.Editor? = withContext(Dispatchers.IO) {
        val encryptedData = encryptToken(accessToken)
        val encryptedRefreshData = encryptToken(refreshToken)
        if (encryptedData != null) {
            sharedPreferences.edit().apply {
                // Save encrypted access token and IV
                putString(keyAccessToken, encryptedData.first)
                putString(ivAccessToken, encryptedData.second)
                // Save encrypted refresh token and IV
                putString(keyRefreshToken, encryptedRefreshData?.first)
                putString(ivRefreshToken, encryptedRefreshData?.second)
                apply()
            }
        } else {
            throw RuntimeException("Failed to encrypt access token")
        }
    }

    //Get token
     fun getAccessToken(): String?  {
        val encryptedToken = sharedPreferences.getString(keyAccessToken, null)
        val iv = sharedPreferences.getString(ivAccessToken, null)
        if (encryptedToken != null && iv != null) {
            return decryptToken(encryptedToken, iv)
        }
        return null
    }

    //Get refresh token
    suspend fun getRefreshToken(): String? = withContext(Dispatchers.IO) {
        val encryptedToken = sharedPreferences.getString(keyRefreshToken, null)
        val iv = sharedPreferences.getString(ivRefreshToken, null)
        if (encryptedToken != null && iv != null) {
            decryptToken(encryptedToken, iv)
        }
        return@withContext null
    }
}