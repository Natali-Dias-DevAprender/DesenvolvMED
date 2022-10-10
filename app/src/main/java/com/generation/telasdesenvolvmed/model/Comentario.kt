package com.generation.telasdesenvolvmed.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

data class Comentario(
    val id: Long,
    var conteudo: String,
    val data: String,
    val postagem: Postagem,
    val cadastro: Cadastro
        ){

}