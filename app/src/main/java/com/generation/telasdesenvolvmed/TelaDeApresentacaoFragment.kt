package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentTelaDeApresentacaoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TelaDeApresentacaoFragment : Fragment() {

    private lateinit var binding: FragmentTelaDeApresentacaoBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTelaDeApresentacaoBinding.inflate(layoutInflater, container, false)

        binding.button2.setOnClickListener {
            mainViewModel.selectLogin.observe(viewLifecycleOwner) {
                verificaLogin()
            }

        }

        return binding.root
    }

    private fun verificaLogin() {

        if (mainViewModel.selectLogin.value?.size!! >= 1) {

            val email = mainViewModel.selectLogin.value?.get(0)?.email.toString()

            mainViewModel.getCadastroByEmail(email)

            mainViewModel.cadastroVerificado.observe(viewLifecycleOwner) { response ->
                if (response.body() != null) {
                    findNavController().navigate(R.id.action_tela_de_apresentacaoFragment_to_postFragment)
                }
            }

        } else {
            findNavController().navigate(R.id.action_tela_de_apresentacaoFragment_to_loginFragment)
        }
    }
}