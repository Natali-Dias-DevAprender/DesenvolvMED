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

        //findNavController().navigate(R.id.action_inicialFragment_to_postFragment)

        return binding.root
    }

}