package com.generation.telasdesenvolvmed.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

data class Comentario(
    val id: Long,
    var conteudo: String,
    val dataComentario: String,
    val postagem: Postagem,
    val cadastro: Cadastro
        ){

}