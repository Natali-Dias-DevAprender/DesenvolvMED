package com.generation.telasdesenvolvmed

import android.os.Bundle
import android.security.identity.CredentialDataResult
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.generation.telasdesenvolvmed.data.Login
import com.generation.telasdesenvolvmed.databinding.FragmentDadosPessoaisBinding
import com.generation.telasdesenvolvmed.model.MedicoCadastro
import com.generation.telasdesenvolvmed.model.PacienteCadastro
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class DadosPessoaisFragment : Fragment() {

    private lateinit var binding: FragmentDadosPessoaisBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDadosPessoaisBinding.inflate(layoutInflater, container, false)


        defineSpinner()

        binding.spinnerOpcoes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.textDadoAlterar.hint = binding.spinnerOpcoes.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerOpcoesP.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    binding.textDadoAlterar.hint = binding.spinnerOpcoesP.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        binding.botaoAlterar.setOnClickListener {
            atualizaCadastro()
        }

        binding.botaoVoltarDadosPessoais.setOnClickListener {
            findNavController().navigate(R.id.action_dadosPessoaisFragment_to_perfilFragment)
        }

        return binding.root
    }

    private fun defineSpinner() {
        if (mainViewModel.medicoLogado.value?.body()?.crm == null) {
            binding.spinnerOpcoes.visibility = View.INVISIBLE
            binding.spinnerOpcoesP.visibility = View.VISIBLE
        }
    }

    private fun atualizaCadastro() {

        var parametro = binding.textDadoAlterar.text.toString()
        binding.textDadoAlterar.text.clear()

        if (mainViewModel.medicoLogado.value?.body()?.crm == null) {
            if (binding.spinnerOpcoesP.selectedItem.toString() == "Nome") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attPaciente(
                        PacienteCadastro(
                            mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString(),
                            parametro,
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.convenio.toString(),
                        ), mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Nome atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.nome = parametro
                } else {
                    Toast.makeText(context, "Nome Incorreto", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.spinnerOpcoesP.selectedItem.toString() == "Sobrenome") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attPaciente(
                        PacienteCadastro(
                            mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome.toString(),
                            parametro,
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.convenio.toString(),
                        ), mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Sobrenome atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.sobrenome = parametro
                } else {
                    Toast.makeText(context, "Sobrenome Incorreto", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.spinnerOpcoesP.selectedItem.toString() == "E-mail") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.getCadastroAtualizaEmail(parametro)
                    mainViewModel.cadastroEmailAtivo.observe(viewLifecycleOwner) {

                            response ->
                        if (response.code() == 404) {
                            mainViewModel.attPaciente(
                                PacienteCadastro(
                                    mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                                    mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString(),
                                    mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome.toString(),
                                    mainViewModel.pacienteLogado.value?.body()?.cadastro?.sobrenome.toString(),
                                    mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString(),
                                    parametro,
                                    mainViewModel.pacienteLogado.value?.body()?.convenio.toString(),
                                ), parametro
                            )
                            mainViewModel.nukeLogin()
                            mainViewModel.selectLogin.observe(viewLifecycleOwner) { response ->
                                if (response.size == 0) {
                                    mainViewModel.addLogin(
                                        Login(
                                            0,
                                            parametro,
                                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString()
                                        )
                                    )
                                    Toast.makeText(
                                        context,
                                        "E-mail Atualizado com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    mainViewModel.cadastroVerificado.value?.body()?.email =
                                        parametro
                                }
                            }
                        } else {
                            Toast.makeText(context, "E-mail Invalido", Toast.LENGTH_SHORT).show()
                        }
                        println(response)
                    }
                }

            } else if (binding.spinnerOpcoesP.selectedItem.toString() == "Convênio") {
                if (parametro.isNotBlank() && parametro.length in 0..255) {
                    mainViewModel.attPaciente(
                        PacienteCadastro(
                            mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString(),
                            parametro
                        ), mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Convenio atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.pacienteLogado.value?.body()?.convenio = parametro
                } else {
                    Toast.makeText(context, "Convenio Incorreto", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attPaciente(
                        PacienteCadastro(
                            mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            parametro,
                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.pacienteLogado.value?.body()?.convenio.toString(),
                        ), mainViewModel.pacienteLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.senha = parametro
                } else {
                    Toast.makeText(context, "Senha Invalida", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if (binding.spinnerOpcoes.selectedItem.toString() == "Nome") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attMedico(
                        MedicoCadastro(
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                            parametro,
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                        ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Nome atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.nome = parametro
                } else {
                    Toast.makeText(context, "Nome Incorreto", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.spinnerOpcoes.selectedItem.toString() == "Sobrenome") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attMedico(
                        MedicoCadastro(
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                            parametro,
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                        ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Sobrenome atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.sobrenome = parametro
                } else {
                    Toast.makeText(context, "Sobrenome Incorreto", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.spinnerOpcoes.selectedItem.toString() == "E-mail") {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.viewModelScope.launch {
                        mainViewModel.getCadastroAtualizaEmail(parametro)
                        delay(2000)
                    }
                    mainViewModel.cadastroEmailAtivo.observe(viewLifecycleOwner) {
                            response ->
                        if (response.code() == 404) {
                            mainViewModel.attMedico(
                                MedicoCadastro(
                                    mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                                    mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                                    mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                                    mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                                    mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                                    parametro,
                                    mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                                ), parametro
                            )
                            mainViewModel.nukeLogin()
                            mainViewModel.selectLogin.observe(viewLifecycleOwner) { response ->
                                if (response.size == 0) {
                                    mainViewModel.addLogin(
                                        Login(
                                            0,
                                            parametro,
                                            mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha.toString()
                                        )
                                    )
                                    Toast.makeText(
                                        context,
                                        "E-mail Atualizado com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    mainViewModel.cadastroVerificado.value?.body()?.email =
                                        parametro
                                }
                            }
                        } else {
                            Toast.makeText(context, "E-mail Invalido", Toast.LENGTH_SHORT).show()
                        }
                        println(response)
                    }
                }
            }

                /*
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attMedico(
                        MedicoCadastro(
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                            parametro,
                            mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                        ), parametro
                    )
                    mainViewModel.cadastroVerificado.value?.body()?.email = parametro
                } else {
                    Toast.makeText(context, "E-mail Incorreto", Toast.LENGTH_SHORT).show()
                }*/
            else {
                if (parametro.isNotBlank() && parametro.length in 1..255) {
                    mainViewModel.attMedico(
                        MedicoCadastro(
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                            parametro,
                            mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                            mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                        ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString()
                    )
                    Toast.makeText(context, "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show()
                    mainViewModel.cadastroVerificado.value?.body()?.senha = parametro
                } else {
                    Toast.makeText(context, "Senha Invalida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}