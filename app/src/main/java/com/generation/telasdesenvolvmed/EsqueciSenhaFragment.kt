package com.generation.telasdesenvolvmed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentEsqueciSenhaBinding
import com.generation.telasdesenvolvmed.databinding.FragmentLoginBinding

class EsqueciSenhaFragment : Fragment(){

    private lateinit var binding: FragmentEsqueciSenhaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View{
        binding = FragmentEsqueciSenhaBinding.inflate(layoutInflater, container, false)

        binding.botaoVoltarLogin.setOnClickListener {
            findNavController().navigate(R.id.action_esqueciSenhaFragment_to_loginFragment)
        }

        return binding.root
    }
}