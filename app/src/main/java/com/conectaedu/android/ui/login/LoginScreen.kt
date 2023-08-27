package com.conectaedu.android.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conectaedu.android.R
import com.conectaedu.android.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = uiState.isSuccess) {
        if (uiState.isSuccess != null && uiState.isSuccess) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = stringResource(id = R.string.login_title)) },
                /*navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },*/
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(state = rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.login_subtitle),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailTextChanged(it) },
                label = {
                    Text(text = stringResource(id = R.string.login_email_field))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                isError = !uiState.isValidEmail,
                supportingText = {
                    if (!uiState.isValidEmail) {
                        Text(text = stringResource(id = R.string.login_email_error))
                    }
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordTextChanged(it) },
                label = {
                    Text(text = stringResource(id = R.string.login_password_field))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Password, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.onPasswordVisibilityChanged() }) {
                        val icon =
                            if (uiState.showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility

                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                enabled = !uiState.isLoading,
                isError = !uiState.isValidPassword,
                supportingText = {
                    if (!uiState.isValidPassword) {
                        Text(text = stringResource(id = R.string.login_password_error))
                    }
                },
                visualTransformation = if (!uiState.showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.clearFocus()
                }),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            val allFieldValid = uiState.isValidEmail && uiState.isValidPassword

            Button(
                onClick = { viewModel.onLogin() },
                enabled = !uiState.isLoading && allFieldValid,
                modifier = if (uiState.isLoading) Modifier else Modifier.fillMaxWidth()
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = stringResource(id = R.string.login_button))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = {
                    if (navController.previousBackStackEntry?.destination?.route == Screen.Register.route) {
                        navController.navigateUp()
                    } else {
                        navController.navigate(route = Screen.Register.route)
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.login_register_option))
            }
        }
    }
}