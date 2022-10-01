package com.generation.telasdesenvolvmed.model

data class Comentario (
    val id : Number,
    var conteudo : String,
    val data : String,
    val postagem : Postagem,
    val cadastro : Cadastro
        ){

}