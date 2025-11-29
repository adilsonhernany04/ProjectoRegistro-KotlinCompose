package com.universidade.project_form.ui.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TelaLogin(
    aoFazerLogin: (email: String, palPasse: String) -> Unit,
    aoNavegarParaRegistro: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var palPasse by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Login - Admin", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(26.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = palPasse,
            onValueChange = { palPasse = it },
            label = { Text("Palavra-passe") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                aoFazerLogin(email, palPasse)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = aoNavegarParaRegistro) {
            Text("Criar conta")
        }
    }
}