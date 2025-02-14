package com.karine.eventosapp.ui.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewmodel: AuthViewModel,
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("test@test.com") }
    var password by remember { mutableStateOf("test@test.com") }

    LaunchedEffect(viewmodel.logged.value) {
        if (viewmodel.logged.value) {
            onLogin()
        }
    }

    LaunchedEffect(viewmodel.error.value) {
        if (viewmodel.error.value) {
            Toast.makeText(
                context,
                "E-mail ou senha incorretos",
                Toast.LENGTH_SHORT
            ).show()
            delay(300)
            viewmodel.resetError()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("Senha") }
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                viewmodel.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF17a34a)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = onRegister) {
            Text("Criar conta", color = Color(0xFF17a34a))
        }


    }
}

