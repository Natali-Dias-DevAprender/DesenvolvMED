package com.generation.telasdesenvolvmed.api

import com.generation.telasdesenvolvmed.data.Login
import com.generation.telasdesenvolvmed.data.LoginDao
import com.generation.telasdesenvolvmed.model.*
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(private val loginDao: LoginDao) {

    suspend fun listTema(): Response<List<Tema>> {
        return RetrofitInstance.api.listTema()
    }

    suspend fun listPostagem(): Response<List<Postagem>> {
        return RetrofitInstance.api.listPostagem()
    }

    suspend fun addPostagem(postagem: Postagem): Response<Postagem> {
        return RetrofitInstance.api.addPostagem(postagem)
    }

    suspend fun updatePostagem(postagem: Postagem): Response<Postagem> {
        return RetrofitInstance.api.updatePostagem(postagem)
    }

    suspend fun listComentario(id: Long): Response<List<Comentario>> {
        return RetrofitInstance.api.listComentario(id)
    }

    suspend fun addComentario(comentario: Comentario): Response<Comentario> {
        return RetrofitInstance.api.addComentario(comentario)
    }

    suspend fun updateComentario(comentario: Comentario): Response<Comentario> {
        return RetrofitInstance.api.updateComentario(comentario)
    }

    suspend fun getCadastroPacienteByEmail(email: String): Response<Paciente> {
        return RetrofitInstance.api.getCadastroPacienteByEmail(email)
    }

    suspend fun getCadastroMedicoByEmail(email: String): Response<Medico> {
        return RetrofitInstance.api.getCadastroMedicoByEmail(email)
    }

    suspend fun getCadastroByEmail(email: String): Response<Cadastro> {
        return RetrofitInstance.api.getCadastroByEmail(email)
    }

    suspend fun addPaciente(paciente: PacienteCadastro): Response<PacienteCadastro> {
        return RetrofitInstance.api.addPaciente(paciente)
    }

    suspend fun addMedico(medico: MedicoCadastro): Response<MedicoCadastro> {
        return RetrofitInstance.api.addMedico(medico)
    }

    suspend fun attPaciente(paciente: PacienteCadastro): Response<PacienteCadastro> {
        return RetrofitInstance.api.attPaciente(paciente)
    }

    suspend fun attMedico(medico: MedicoCadastro): Response<MedicoCadastro> {
        return RetrofitInstance.api.attMedico(medico)
    }

    val selectLogin = loginDao.selectLogin()

    fun addLogin(login: Login) {
        loginDao.addLogin(login)
    }

    fun nukeTable() {
        loginDao.nukeTable()
    }

    suspend fun deletaPostagem(id: Long): Response<Postagem> {
        return RetrofitInstance.api.deletaPostagem(id)
    }

    suspend fun deletaComentario(id: Long): Response<Comentario> {
        return  RetrofitInstance.api.deletaComentario(id)
    }
}