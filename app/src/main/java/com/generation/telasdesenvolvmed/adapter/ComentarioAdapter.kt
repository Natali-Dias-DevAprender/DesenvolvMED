package com.generation.telasdesenvolvmed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.telasdesenvolvmed.MainViewModel
import com.generation.telasdesenvolvmed.databinding.ItemDeComentariosBinding
import com.generation.telasdesenvolvmed.model.Comentario

class ComentarioAdapter(
	private val comentarioClickListener: ComentarioClickListener,
	val mainViewModel: MainViewModel
) : RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder>() {

	private var listComentario = emptyList<Comentario>()

	class ComentarioViewHolder(val binding: ItemDeComentariosBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
		return ComentarioViewHolder(
			ItemDeComentariosBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
		val comentario = listComentario[position]

		holder.binding.textNome.text = comentario.cadastro.nome
		holder.binding.textConteudo.text = comentario.conteudo

		holder.binding.botaoEditarComentario.setOnClickListener {
			comentarioClickListener.onComentarioClickListener(comentario)
		}
	}

	override fun getItemCount(): Int {
		return listComentario.size
	}

	fun setList(list: List<Comentario>) {
		listComentario = list.sortedByDescending { it.id }
		notifyDataSetChanged()
	}
}