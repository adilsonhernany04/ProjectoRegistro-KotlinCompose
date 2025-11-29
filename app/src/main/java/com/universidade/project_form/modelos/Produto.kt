package com.universidade.project_form.modelos

data class Produto (
    val id: Long = 0L,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val preco: Double,
    val imagem: String? = null
)

