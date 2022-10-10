package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentCriarComentarioBinding
import com.generation.telasdesenvolvmed.model.Comentario
import com.generation.telasdesenvolvmed.model.Postagem
import java.time.LocalDateTime


class ComentariosFragment : Fragment() {

	private lateinit var binding: FragmentCriarComentarioBinding
	private val mainViewModel: MainViewModel by activityViewModels()
	private var postagemSelecionada: Postagem? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentCriarComentarioBinding.inflate(layoutInflater, container, false)

		mainViewModel.listComentario()

		binding.buttonComentar.setOnClickListener {
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

		val conteudo = binding.editConteudo.text.toString()
		val data = LocalDateTime.now().toString()
		val postagem = postagemSelecionada!!
		val cadastro = mainViewModel.cadastroVerificado.value?.body()

		if (validarCampos(conteudo)) {
			val salvar: String
			if (postagemSelecionada != null) {
				val comentario = Comentario(0, conteudo, data, postagem, cadastro!!)
				mainViewModel.addComentario(comentario)
				Toast.makeText(context, "Comentário realizado!", Toast.LENGTH_SHORT).show()
				findNavController().navigate(R.id.action_comentariosFragment_to_postFragment)
			}
		} else {
			Toast.makeText(context, "Comentário realizado!", Toast.LENGTH_SHORT).show()
		}
	}
}