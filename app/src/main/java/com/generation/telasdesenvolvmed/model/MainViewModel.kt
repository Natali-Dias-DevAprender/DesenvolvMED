package com.generation.telasdesenvolvmed.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.telasdesenvolvmed.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _myComentarioResponse = MutableLiveData<Response<List<Comentario>>>()
    val myComentarioResponse: LiveData<Response<List<Comentario>>> = _myComentarioResponse

    fun listComentario() {
        viewModelScope.launch {
            try {

                val response = repository.listComentario()
                _myComentarioResponse.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addComentario(comentario: Comentario) {
        viewModelScope.launch {
            try {

                repository.addComentario(comentario)

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }
}