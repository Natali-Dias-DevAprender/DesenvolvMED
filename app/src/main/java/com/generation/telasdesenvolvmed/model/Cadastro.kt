package com.generation.telasdesenvolvmed.model

//MURILLO

class Cadastro (
	val id: Long,
	val cpf: String,
	val nome: String,
	val sobrenome: String,
	val senha: String,
	val email: String,
	val comentarios: List<Comentario>?
		){
}