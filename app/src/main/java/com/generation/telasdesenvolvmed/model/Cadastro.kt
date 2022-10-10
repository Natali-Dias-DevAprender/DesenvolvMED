package com.generation.telasdesenvolvmed.model

class Cadastro (
    val id: Long,
    val cpf: String,
    var nome: String,
    var sobrenome: String,
    var senha: String,
    var email: String,
    val comentarios: List<Comentario>?
		){
}