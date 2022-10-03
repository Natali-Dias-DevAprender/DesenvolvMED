package com.generation.telasdesenvolvmed.api

import com.generation.telasdesenvolvmed.model.Comentario
import com.generation.telasdesenvolvmed.model.Postagem
import retrofit2.Response
import retrofit2.Retrofit

class Repository {

    suspend fun listPostagem(): Response<List<Postagem>> {
        return RetrofitInstance.api.listPostagem()
    }

    suspend fun addPostagem(postagem: Postagem): Response<Postagem> {
        return RetrofitInstance.api.addPostagem(postagem)
    }

    suspend fun listComentario(): Response<List<Comentario>> {
        return RetrofitInstance.api.listComentario()
    }

    suspend fun addComentario(comentario: Comentario): Response<Comentario> {
        return RetrofitInstance.api.addComentario(comentario)
    }
}