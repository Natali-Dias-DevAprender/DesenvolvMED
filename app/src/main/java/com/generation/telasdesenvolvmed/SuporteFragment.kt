package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.databinding.FragmentSobreBinding
import com.generation.telasdesenvolvmed.databinding.FragmentSuporteBinding

class SuporteFragment : Fragment() {

    private lateinit var binding: FragmentSuporteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuporteBinding.inflate(layoutInflater, container, false)

        binding.botaoVoltarSuporte.setOnClickListener {
            findNavController().navigate(R.id.action_suporteFragment_to_perfilFragment)
        }

        return binding.root
    }

}