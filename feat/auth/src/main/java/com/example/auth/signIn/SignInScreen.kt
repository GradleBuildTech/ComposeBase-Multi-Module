package com.example.auth.signIn

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.signIn.controller.SigInUiState
import com.example.auth.signIn.controller.SignInStateData
import com.example.auth.signIn.controller.SignInUiEvent
import com.example.auth.signIn.controller.SignInViewModel
import com.example.core.components.BuildButton
import com.example.core.components.textField.BuildOutlineTextField
import com.example.core.extensions.coloredShadow
import com.example.core.lib.constants.DesignSystem
import com.example.core.lib.constants.DrawableConst

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by signInViewModel.uiState.collectAsState()
    val scrollState: ScrollState = rememberScrollState()
    val context = LocalContext.current


    LaunchedEffect(uiState) {
        when (uiState.state) {
            SignInStateData.SUCCESS -> {
                Log.d("com.example.auth.signIn.SignInScreen", "Sign in success")
                signInViewModel.onEvent(SignInUiEvent.NavigateToHome)
            }

            SignInStateData.ERROR -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()

            }

            else -> {}
        }
    }

    SignInScreen(
        modifier = Modifier,
        signInUiState = uiState,
        verticalScrollState = scrollState,
        onSignIn = { email, password ->
            signInViewModel.onEvent(SignInUiEvent.SignIn(email, password))
        }
    )
}

@Composable
internal fun SignInScreen(
    modifier: Modifier,
    signInUiState: SigInUiState,
    verticalScrollState: ScrollState,
    onSignIn: (String, String) -> Unit
) {
    val textController = remember { mutableStateOf("phhai@ymail.com") }
    val passwordController = remember { mutableStateOf("123456") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeaderAuthView(modifier = Modifier)
        SignInForm(
            modifier = Modifier,
            verticalScrollState = verticalScrollState,
            emailController = textController.value,
            passwordController = passwordController.value,
            onSignIn = { onSignIn(textController.value, passwordController.value) },
            onEmailChange = { textController.value = it },
            onPasswordChange = { passwordController.value = it },
            isLoading = signInUiState.state == SignInStateData.LOADING
        )
    }

}

@Composable
internal fun HeaderAuthView(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .coloredShadow(color = Color.Black, offsetY = 2.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(DesignSystem.INSIDE_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "LetTutor",
            style = DesignSystem.TITLE_LARGE_STYLE.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
        )

    }
}

@Composable
internal fun SignInForm(
    modifier: Modifier,
    verticalScrollState: ScrollState,
    isLoading: Boolean,
    emailController: String,
    passwordController: String,
    onSignIn: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    val textFileModifier = Modifier
        .fillMaxWidth()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(DesignSystem.INSIDE_PADDING)
            .verticalScroll(verticalScrollState)
    ) {
        Image(
            painter = painterResource(id = DrawableConst.IMG_LOGIN),
            contentDescription = "Login",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(DesignSystem.MEDIUM_SPACE_BETWEEN))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Say hello to your English tutors", style = DesignSystem.TITLE_LARGE_STYLE.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(DesignSystem.SPACE_BETWEEN))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Become fluent faster through one on one video chat lessons tailored to your goals",
            style = DesignSystem.TITLE_SMALL_STYLE.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.W400
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(DesignSystem.MEDIUM_SPACE_BETWEEN))
        BuildOutlineTextField(
            modifier = textFileModifier,
            textController = emailController,
            onValueChange = onEmailChange,
            hintText = "Email",
            trailingIcon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(DesignSystem.SPACE_BETWEEN))
        BuildOutlineTextField(
            modifier = textFileModifier,
            textController = passwordController,
            onValueChange = onPasswordChange,
            hintText = "Password",
            trailingIcon = Icons.Default.Lock
        )
        Spacer(modifier = Modifier.height(DesignSystem.MEDIUM_SPACE_BETWEEN))
        BuildButton(
            onPress = onSignIn,
            loading = isLoading
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = "Sign In",
                style = DesignSystem.TITLE_SMALL_STYLE.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}