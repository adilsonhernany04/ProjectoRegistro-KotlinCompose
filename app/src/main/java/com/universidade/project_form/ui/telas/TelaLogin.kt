package com.universidade.project_form.ui.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLogin(
    aoFazerLogin: (email: String, palPasse: String) -> Unit,
    aoNavegarParaRegistro: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var palPasse by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login - Admin") }
            )
        },

    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp),

        ) {

            Spacer(modifier = Modifier.height(60.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                value = palPasse,
                onValueChange = { palPasse = it },
                label = { Text("Palavra-passe") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier
                .height(20.dp))

            Button(
                onClick = {
                    aoFazerLogin(email, palPasse)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Text("Entrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = aoNavegarParaRegistro) {
                Text("Ver Dev")
            }
        }
    }
}
