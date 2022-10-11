package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentEditarComentarioBinding
import com.generation.telasdesenvolvmed.model.Comentario
import com.generation.telasdesenvolvmed.model.Postagem
import java.time.LocalDateTime

class EditarComentarioFragment : Fragment() {

	private lateinit var binding : FragmentEditarComentarioBinding
	private val mainViewModel : MainViewModel by activityViewModels()
	private var postagemSelecionada: Postagem? = null
	var comentarioSelecionado: Comentario? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentEditarComentarioBinding.inflate(layoutInflater, container, false)

		mainViewModel.comentarioSelecionado

		binding.imageButtonComentar.setOnClickListener {
			inserirNoBanco()
		}

		return binding.root
	}

	private fun validarCampos(conteudo: String): Boolean {
		return (
				(conteudo.isNotBlank() && conteudo.length in 10..300)
				)
	}

	private fun inserirNoBanco() {

		val conteudo = binding.escrevaComentarioInput.text.toString()
		val data = LocalDateTime.now().toString()
		val postagem = postagemSelecionada!!
		val cadastro = mainViewModel.cadastroVerificado.value?.body()

		if (validarCampos(conteudo)) {
			val salvar : String
			if (comentarioSelecionado != null) {
				salvar = "Comentário editado!"
				val comentario = Comentario(comentarioSelecionado?.id!!, conteudo, data, postagem, cadastro!!)
				mainViewModel.updateComentario(comentario)
				Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
				findNavController().navigate(R.id.action_editarComentarioFragment_to_comentariosFragment)

			} else {
				salvar = "Comentário não pode estar em branco!"
				Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
			}
		}


	}


}