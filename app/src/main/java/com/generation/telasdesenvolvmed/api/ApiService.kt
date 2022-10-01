package com.generation.telasdesenvolvmed.api

import com.generation.telasdesenvolvmed.model.Comentario
import com.generation.telasdesenvolvmed.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("postagem")
    suspend fun listPostagem(): Response<List<Postagem>>

    @POST("postagem")
    suspend fun addPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>

    @GET("comentario")
    suspend fun listComentario(): Response<List<Comentario>>

    @POST("comentario")
    suspend fun addComentario(
        @Body comentario: Comentario
    ): Response<Comentario>

}