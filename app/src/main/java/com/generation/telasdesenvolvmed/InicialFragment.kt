package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentInicialBinding


class InicialFragment : Fragment() {

    private lateinit var binding: FragmentInicialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicialBinding.inflate(layoutInflater, container, false)

        binding.imageView.alpha = 0f

        binding.imageView.animate().setDuration(3000).alpha(1f).withEndAction {
            activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        binding.imageView.animate().setDuration(3000).alpha(1f).withEndAction {
            findNavController().navigate(R.id.action_inicialFragment_to_tela_de_apresentacaoFragment)
        }

        return binding.root
    }
}