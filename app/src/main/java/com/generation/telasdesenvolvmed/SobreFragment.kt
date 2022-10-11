package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentPerfilBinding
import com.generation.telasdesenvolvmed.databinding.FragmentSobreBinding


class SobreFragment : Fragment() {

    private lateinit var binding: FragmentSobreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSobreBinding.inflate(layoutInflater, container, false)

        binding.botaoVoltarSobre.setOnClickListener {
            findNavController().navigate(R.id.action_sobreFragment_to_perfilFragment)
        }

        return binding.root
    }

}