package com.generation.telasdesenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.generation.telasdesenvolvmed.data.LoginDatabase
import com.generation.telasdesenvolvmed.databinding.FragmentPerfilBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(layoutInflater, container, false)

        getDados()

        binding.botaoSobre.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_sobreFragment)
        }

        binding.botaoSuporte.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_suporteFragment)
        }

        binding.botaoDadosPessoais.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_dadosPessoaisFragment)
        }

        binding.buttonLogout.setOnClickListener {
            mainViewModel.nukeLogin()

            mainViewModel.selectLogin.observe(viewLifecycleOwner){
                response -> if(response.size == 0){
                println("O tamanho do banco de dados local eh: "+mainViewModel.selectLogin.value?.size!!)
                mainViewModel.cadastroVerificado.value?.body()?.email = ""
                mainViewModel.medicoLogado.value?.body()?.cadastro?.email = ""
                mainViewModel.pacienteLogado.value?.body()?.cadastro?.email = ""

                mainViewModel.getCadastroByEmail("")
                findNavController().navigate(R.id.action_perfilFragment_to_inicialFragment)
                /*
                mainViewModel.viewModelScope.launch {
                    delay(3000)
                    mainViewModel.cadastroVerificado.observe(viewLifecycleOwner){
                            response -> if (response.body() == null) {
                        findNavController().navigate(R.id.action_perfilFragment_to_inicialFragment)
                    }
                    }
                }*/
                }
            }


        }


        return binding.root
    }

    private fun getDados(){
        binding.textPerfilNome.text = mainViewModel.cadastroVerificado.value?.body()?.nome.toString() + " " + mainViewModel.cadastroVerificado.value?.body()?.sobrenome.toString()
        binding.textPerfilCpf.text = mainViewModel.cadastroVerificado.value?.body()?.cpf.toString()
        binding.textPerfilEmail.text = mainViewModel.cadastroVerificado.value?.body()?.email.toString()

        if(mainViewModel.medicoLogado.value?.body()?.crm == null){
            binding.textExclusivo.text = "Convenio: "
            binding.textPerfilExclusivo.text = mainViewModel.pacienteLogado.value?.body()?.convenio.toString()
        } else{
            binding.textExclusivo.text = "CRM: "
            binding.textPerfilExclusivo.text = mainViewModel.medicoLogado.value?.body()?.crm.toString()
        }

    }

}