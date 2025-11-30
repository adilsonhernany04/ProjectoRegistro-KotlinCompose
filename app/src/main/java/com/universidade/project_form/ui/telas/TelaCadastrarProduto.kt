@file:OptIn(ExperimentalMaterial3Api::class)

package com.universidade.project_form.ui.telas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.universidade.project_form.modelos.Categoria
import com.universidade.project_form.modelos.Produto
import kotlinx.coroutines.launch

@Composable
fun TelaCadastrarProduto(
    aoRegistrarProduto: (Produto) -> Unit,
    aoVoltar: () -> Unit
) {

    // ----- ESTADOS -----
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf<Categoria?>(null) }
    var descricao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var imagemUri by remember { mutableStateOf<String?>(null) }

    // dropdown
    var categoriaExpanded by remember { mutableStateOf(false) }

    // snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // launcher estabilizado
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagemUri = uri?.toString()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Produto") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // ========== IMAGEM ==========
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imagemUri == null) {
                    Text("Clique para adicionar imagem")
                } else {
                    AsyncImage(
                        model = remember(imagemUri) { imagemUri },
                        contentDescription = "Imagem do produto",
                        modifier = Modifier.fillMaxWidth().height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ========== Nome ==========
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do produto") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ========== Dropdown Categorias ==========
            ExposedDropdownMenuBox(
                expanded = categoriaExpanded,
                onExpandedChange = { expanded -> categoriaExpanded = expanded }
            ) {

                OutlinedTextField(
                    value = categoria?.mostrarNome ?: "Seleccione a categoria",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoria") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoriaExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(


                    )
                )

                ExposedDropdownMenu(
                    expanded = categoriaExpanded,
                    onDismissRequest = { categoriaExpanded = false }
                ) {
                    Categoria.entries.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.mostrarNome) },
                            onClick = {
                                categoria = item
                                categoriaExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ========== Descrição ==========
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ========== Preço ==========
            OutlinedTextField(
                value = preco,
                onValueChange = { preco = it },
                label = { Text("Preço") },
                modifier = Modifier.fillMaxWidth(),


            )

            Spacer(modifier = Modifier.height(24.dp))

            // ========== Botão ==========
            Button(
                onClick = {
                    when {
                        nome.isBlank() -> {
                            scope.launch {
                                snackbarHostState.showSnackbar("Nome não pode estar vazio")
                            }
                        }
                        categoria == null -> {
                            scope.launch {
                                snackbarHostState.showSnackbar("Selecione uma categoria")
                            }
                        }
                        preco.toDoubleOrNull() == null -> {
                            scope.launch {
                                snackbarHostState.showSnackbar("Preço inválido")
                            }
                        }
                        else -> {
                            aoRegistrarProduto(
                                Produto(
                                    nome = nome,
                                    categoria = categoria!!.toString(),
                                    descricao = descricao,
                                    preco = preco.toDouble(),
                                    imagem = imagemUri
                                )
                            )

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Produto cadastrado com sucesso!",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cadastrar")
            }
        }
    }
}
