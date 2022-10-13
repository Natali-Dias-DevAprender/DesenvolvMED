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
import com.generation.telasdesenvolvmed.data.Login
import com.generation.telasdesenvolvmed.databinding.FragmentCadastroBinding
import com.generation.telasdesenvolvmed.model.MedicoCadastro
import com.generation.telasdesenvolvmed.model.PacienteCadastro
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCadastroBinding.inflate(layoutInflater, container, false)

        binding.voltarButton.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
        }

        binding.radioMedico.setOnClickListener {
            binding.inputExclusivo.text?.clear()
            binding.inputExclusivoLayout.visibility = View.VISIBLE
            binding.inputExclusivo.hint = "CRM"
            binding.buttonCadastrar.visibility = View.VISIBLE
        }

        binding.radioPaciente.setOnClickListener {
            binding.inputExclusivo.text?.clear()
            binding.inputExclusivoLayout.visibility = View.VISIBLE
            binding.inputExclusivo.hint = "Convênio (opcional)"
            binding.buttonCadastrar.visibility = View.VISIBLE
        }

        binding.buttonCadastrar.setOnClickListener {
            if (binding.radioPaciente.isChecked) {
                cadastroPaciente()
            } else {
                cadastroMedico()
            }
        }

        return binding.root
    }

    private fun validarCampos(
        nome: String,
        cpf: String,
        sobrenome: String,
        email: String,
        senha: String,
        crm: String
    ): Boolean {

        return (
                (nome.isNotBlank() && nome.length in 1..255) &&
                        (cpf.isNotBlank() && cpf.length == 11) &&
                        (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                        (email.isNotBlank() && email.length in 1..255) &&
                        (senha.isNotBlank() && senha.length in 1..255) &&
                        (crm.isNotBlank() && crm.length == 13)
                )
    }

    private fun cadastroMedico() {
        val nome = binding.tilNome.text.toString()
        val cpf = binding.tilCpf.text.toString()
        val sobrenome = binding.tilSobrenome.text.toString()
        val email = binding.tilEmail.text.toString()
        val senha = binding.tilSenha.text.toString()
        val crm = binding.inputExclusivo.text.toString()
        val confirmaSenha = binding.tilConfirmaSenha.text.toString()

        mainViewModel.getCadastro(email)
        var emailNovo = false

        mainViewModel.cadastroVerificado.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                if (!emailNovo) {
                    Toast.makeText(context, "E-mail inválido", Toast.LENGTH_SHORT).show()
                }


            } else {
                emailNovo = true

                if (validarCampos(
                        nome,
                        cpf,
                        sobrenome,
                        email,
                        senha,
                        crm
                    ) && emailNovo && senha == confirmaSenha
                ) {
                    mainViewModel.addMedico(
                        MedicoCadastro(0, cpf, nome, sobrenome, senha, email, crm),
                        email
                    )
                    mainViewModel.getCadastroByEmail(email)
                    mainViewModel.addLogin(Login(0, email, senha))
                    mainViewModel.medicoLogado.observe(viewLifecycleOwner) { responseMedico ->
                        if (responseMedico != null) {
                            mainViewModel.cadastroVerificado.observe(viewLifecycleOwner) {
                                if (mainViewModel.cadastroVerificado.value?.body()?.email == email) {
                                    Toast.makeText(
                                        context,
                                        "Cadastro bem sucedido!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_cadastroFragment_to_postFragment)
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validarCamposPaciente(
        nome: String,
        cpf: String,
        sobrenome: String,
        email: String,
        senha: String,
        convenio: String
    ): Boolean {
        return (
                (nome.isNotBlank() && nome.length in 1..255) &&
                        (cpf.isNotBlank() && cpf.length == 11) &&
                        (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                        (email.isNotBlank() && email.length in 1..255) &&
                        (senha.isNotBlank() && senha.length in 1..255) &&
                        (convenio.isEmpty() || convenio.length > 1)
                )
    }

    private fun cadastroPaciente() {
        val nome = binding.tilNome.text.toString()
        val cpf = binding.tilCpf.text.toString()
        val sobrenome = binding.tilSobrenome.text.toString()
        val email = binding.tilEmail.text.toString()
        val senha = binding.tilSenha.text.toString()
        val convenio = binding.inputExclusivo.text.toString()
        val confirmaSenha = binding.tilConfirmaSenha.text.toString()

        mainViewModel.getCadastro(email)
        var emailNovo = false

        mainViewModel.cadastroVerificado.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                if (!emailNovo) {
                    Toast.makeText(context, "E-mail inválido", Toast.LENGTH_SHORT).show()
                }

            } else {
                emailNovo = true

                if (validarCamposPaciente(
                        nome,
                        cpf,
                        sobrenome,
                        email,
                        senha,
                        convenio
                    ) && emailNovo && senha == confirmaSenha
                ) {
                    mainViewModel.addPaciente(
                        PacienteCadastro(0, cpf, nome, sobrenome, senha, email, convenio),
                        email
                    )
                    mainViewModel.getCadastroByEmail(email)
                    mainViewModel.addLogin(Login(0, email, senha))
                    mainViewModel.pacienteLogado.observe(viewLifecycleOwner) { responsePaciente ->
                        if (responsePaciente != null) {
                            mainViewModel.cadastroVerificado.observe(viewLifecycleOwner) {
                                if (mainViewModel.cadastroVerificado.value?.body()?.email == email) {
                                    Toast.makeText(
                                        context,
                                        "Cadastro bem sucedido!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_cadastroFragment_to_postFragment)
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}