package com.generation.telasdesenvolvmed.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.telasdesenvolvmed.MainViewModel
import com.generation.telasdesenvolvmed.databinding.ItemDeComentariosBinding
import com.generation.telasdesenvolvmed.model.Comentario

class ComentarioAdapter(
	private val comentarioClickListener: ComentarioClickListener,
	val mainViewModel: MainViewModel,
	val context: Context
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

		val idProcurado = mainViewModel.cadastroVerificado.value?.body()?.id!!

		if(idProcurado != comentario.cadastro.id) {
			holder.binding.botaoEditarComentario.visibility = View.INVISIBLE
			holder.binding.botaoDeletarComentario.visibility = View.INVISIBLE
		} else {
			holder.binding.botaoEditarComentario.visibility = View.VISIBLE
			holder.binding.botaoDeletarComentario.visibility = View.VISIBLE
		}

		holder.binding.botaoDeletarComentario.setOnClickListener {
			showAlertDialog(comentario.id)
		}
	}

	private fun showAlertDialog(id: Long) {
		AlertDialog.Builder(context)
			.setTitle("Deletar Comentario")
			.setMessage("Deseja Excluir o Comentario?")
			.setPositiveButton("Sim"){
					_,_ -> mainViewModel.deletaComentario(id, mainViewModel.postagemSelecionada!!.id)
			}
			.setNegativeButton("Não"){
					_,_ ->
			}.show()
	}

	override fun getItemCount(): Int {
		return listComentario.size
	}

	fun setList(list: List<Comentario>) {
		listComentario = list.sortedByDescending { it.id }
		notifyDataSetChanged()
	}
}