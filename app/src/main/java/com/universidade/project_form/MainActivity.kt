@file:Suppress("LocalVariableName")

package com.universidade.project_form

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.universidade.project_form.ui.theme.Project_form

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppNavegacaoTelas(controladorNav = navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormCadastroPreview() {
    Project_form {
        // TelaCadastro(modifier = Modifier)
        AppNavegacaoTelas(rememberNavController())
    }
}