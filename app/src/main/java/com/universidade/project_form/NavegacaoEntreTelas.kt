package com.universidade.project_form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.universidade.project_form.modelos.Produto
import com.universidade.project_form.ui.telas.TelaCadastrarProduto
import com.universidade.project_form.ui.telas.TelaLogin

sealed class Telas(val rota: String) {
    object Login : Telas("login")
    object Cadastro : Telas("add_produto")
}

@Composable
fun AppNavegacaoTelas(controladorNav: NavHostController) {

    // lista produtos
    var listaProduto by remember { mutableStateOf<List<Produto>>(emptyList()) }

    NavHost(
        navController = controladorNav,
        startDestination = Telas.Cadastro.rota
    ) {
        // TELA DE CADASTRAR  PRODUTO
        composable(Telas.Cadastro.rota) {
            TelaCadastrarProduto(
                aoRegistrarProduto = { produto ->
                    // listaProduto = listaProduto + produto
                    controladorNav.popBackStack()
                },
                aoVoltar = { controladorNav.popBackStack() }
            )
        }
    }
}
