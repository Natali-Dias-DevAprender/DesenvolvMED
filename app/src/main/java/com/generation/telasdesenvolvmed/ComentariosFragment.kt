package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.telasdesenvolvmed.adapter.ComentarioAdapter
import com.generation.telasdesenvolvmed.adapter.ComentarioClickListener
import com.generation.telasdesenvolvmed.databinding.FragmentComentariosBinding
import com.generation.telasdesenvolvmed.model.Comentario
import com.generation.telasdesenvolvmed.model.Postagem
import java.time.LocalDateTime


class ComentariosFragment : Fragment(), ComentarioClickListener {

	private lateinit var binding: FragmentComentariosBinding
	private val mainViewModel: MainViewModel by activityViewModels()
	private var postagemSelecionada: Postagem? = null
	var comentarioSelecionado: Comentario? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentComentariosBinding.inflate(layoutInflater, container, false)

		mainViewModel.listComentario()

		val adapter = ComentarioAdapter(this, mainViewModel)
		binding.recyclerView.layoutManager = LinearLayoutManager(context)
		binding.recyclerView.adapter = adapter
		binding.recyclerView.setHasFixedSize(true)

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
			if (postagemSelecionada != null) {
				val comentario = Comentario(0, conteudo, data, postagem, cadastro!!)
				mainViewModel.addComentario(comentario)
				Toast.makeText(context, "Comentário realizado!", Toast.LENGTH_SHORT).show()
				findNavController().navigate(R.id.action_comentariosFragment_to_postFragment)
			}
		} else {
			Toast.makeText(context, "Comentário não pode estar em branco!", Toast.LENGTH_SHORT).show()
		}
	}

	override fun onComentarioClickListener(comentario: Comentario) {
		mainViewModel.comentarioSelecionado = comentario
	}
}