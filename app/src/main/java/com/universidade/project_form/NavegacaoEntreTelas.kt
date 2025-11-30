package com.universidade.project_form

// AUTHOR : Adilson Hernany

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.universidade.project_form.modelos.Produto
import com.universidade.project_form.ui.telas.TelaCadastrarProduto
import com.universidade.project_form.ui.telas.TelaLogin
import kotlinx.coroutines.launch

sealed class Telas(val rota: String) {
    object Login : Telas("login")
    object Cadastro : Telas("add_produto")
}

@Composable
fun AppNavegacaoTelas(controladorNav: NavHostController) {

    // lista produtos
    var listaProduto by remember { mutableStateOf<List<Produto>>(emptyList()) }

    val emailAdmin = "adilsonhernany@gmail.com"
    val palPasseAdmin = "adilsonhernanyadmin"

    // Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        NavHost(
            navController = controladorNav,
            startDestination = Telas.Login.rota,
            modifier = Modifier.padding(paddingValues)
        ) {
            // TELA DE LOGIN
            composable(Telas.Login.rota) {
                TelaLogin(
                    aoFazerLogin = { email, palPasse ->
                        if (email == emailAdmin && palPasse == palPasseAdmin) {
                            controladorNav.navigate(Telas.Cadastro.rota)
                        } else {
                            // MOSTRA SNACKBAR
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Credenciais inválidas!",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                    aoNavegarParaRegistro = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "BY ADILSON HERNANY - COMPUTAÇÃO",

                            )
                        }
                    }
                )
            }

            // TELA DE CADASTRAR PRODUTO
            composable(Telas.Cadastro.rota) {
                TelaCadastrarProduto(
                    aoRegistrarProduto = { produto ->
                        listaProduto = listaProduto + produto
                        controladorNav.popBackStack()
                    },
                    aoVoltar = { controladorNav.popBackStack() }
                )
            }

        }
    }
}
