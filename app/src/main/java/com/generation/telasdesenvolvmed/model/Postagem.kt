package com.generation.telasdesenvolvmed.model

data class Postagem(
    val id: Long,
    val tema: Tema,
    val medico: Medico,
    val titulo: String,
    val conteudo: String,
    val anexo: String
) {

}