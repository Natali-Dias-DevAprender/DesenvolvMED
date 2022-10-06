package com.generation.telasdesenvolvmed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_postFragment)
        }

        binding.buttonEsqueciSenha.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_esqueciSenhaFragment)
        }

        return binding.root



    }

}