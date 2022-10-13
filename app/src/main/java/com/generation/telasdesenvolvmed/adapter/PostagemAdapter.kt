package com.generation.telasdesenvolvmed.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.generation.telasdesenvolvmed.MainViewModel
import com.generation.telasdesenvolvmed.R
import com.generation.telasdesenvolvmed.databinding.CardLayoutBinding
import com.generation.telasdesenvolvmed.model.Postagem

class PostagemAdapter(
    private val postagemClickListener: PostagemClickListener,
    val mainViewModel: MainViewModel,
    val context: Context
) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {

    private var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return PostagemViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = listPostagem[position]

        holder.binding.temaPost.text = postagem.tema?.tema
        holder.binding.nomeMedico.text = postagem.medico?.cadastro!!.nome
        holder.binding.tituloPost.text = postagem.titulo
        holder.binding.conteudoPost.text = postagem.descricao

        Glide
            .with(context)
            .load(postagem.anexo)
            .placeholder(R.drawable.bg_text_input)
            .into(holder.binding.imagePostagem)

        val crmProcurado = mainViewModel.medicoLogado.value?.body()?.crm.toString()

        if (crmProcurado != postagem.medico.crm) {
            holder.binding.botaoEditarPost.visibility = View.INVISIBLE
            holder.binding.botaoDeletarPost.visibility = View.INVISIBLE
        } else {
            holder.binding.botaoEditarPost.visibility = View.VISIBLE
            holder.binding.botaoDeletarPost.visibility = View.VISIBLE
        }

        holder.binding.buttonComentarios.setOnClickListener {
            postagemClickListener.onPostagemParaComentarioClickListener(postagem)
        }

        holder.binding.botaoEditarPost.setOnClickListener {
            postagemClickListener.onPostagemClickListener(postagem)
        }

        if (mainViewModel.pacienteLogado.value?.body()?.toString() != null) {
            holder.binding.botaoEditarPost.visibility = View.INVISIBLE
            holder.binding.botaoDeletarPost.visibility = View.INVISIBLE
        }

        holder.binding.botaoDeletarPost.setOnClickListener {
            showAlertDialog(postagem.id)
        }
    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    fun setList(list: List<Postagem>) {
        listPostagem = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    private fun showAlertDialog(id: Long) {
        AlertDialog.Builder(context)
            .setTitle("Deletar postagem")
            .setMessage("Deseja realmente excluir a postagem?")
            .setPositiveButton("Sim") { _, _ ->
                mainViewModel.deletaPostagem(id)
            }
            .setNegativeButton("Não") { _, _ ->
            }.show()
    }
}