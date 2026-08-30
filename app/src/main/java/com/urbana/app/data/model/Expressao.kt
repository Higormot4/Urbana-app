package com.urbana.app.data.model

data class Expressao(
    val id: Int,
    val nome: String,
    val categoria: String,
    val cidade: String,
    val artista: String,
    val descricao: String,
    val imagemUrl: String
)