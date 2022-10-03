package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.telasdesenvolvmed.adapter.PostagemAdapter
import com.generation.telasdesenvolvmed.databinding.FragmentPostBinding
import com.generation.telasdesenvolvmed.model.Cadastro
import com.generation.telasdesenvolvmed.model.Medico
import com.generation.telasdesenvolvmed.model.Postagem
import com.generation.telasdesenvolvmed.model.Tema


class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPostBinding.inflate(layoutInflater, container, false)

        //binding.dmToolbar.toolbarTitle.text = "DesenvolvMED"

        val listPostagens = listOf(
            Postagem(
                2,
                Tema(1, "Vacina", null ),
                Medico(1, "CRM/SP 123546", Cadastro(1, "01754689720",
                    "Joviraldo", "Robson", "2154154", "jovis@gmail.com",
                    null ),null,),
                "Cerveja",
                "A cerveja é muito importante para a sua saude pois faz parte de 98% do seu organismo",
                "https://imgur.com/vpVts7m"
            ),
            Postagem(
                1,
                Tema(1, "Vacina", null ),
                Medico(1, "CRM/SP 123546", Cadastro(1, "01754689720",
                    "Joviraldo", "Robson", "2154154", "jovis@gmail.com",
                    null ),null,),
                "Sake",
                "A sake é muito importante para a sua saude pois faz parte de 98% do seu organismo",
                "https://imgur.com/vpVts7m"
            ),
        )

        val postagemAdapter = PostagemAdapter()
        binding.recyclerPostagem.layoutManager = LinearLayoutManager(context)
        binding.recyclerPostagem.adapter = postagemAdapter
        binding.recyclerPostagem.setHasFixedSize(true)

        postagemAdapter.setList(listPostagens)

        binding.addPostButton.setOnClickListener{
            findNavController().navigate(R.id.action_postFragment_to_criarPostFragment)
        }

        return binding.root
    }


}