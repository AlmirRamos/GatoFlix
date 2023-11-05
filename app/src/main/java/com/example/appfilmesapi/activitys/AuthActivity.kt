package com.example.appfilmesapi.activitys

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.appfilmesapi.R
import com.example.appfilmesapi.databinding.ActivityAuthBinding
import com.example.appfilmesapi.model.Usuario
import com.example.appfilmesapi.utill.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.firestore.FirebaseFirestore

class AuthActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configCliks()
    }

    private fun configCliks() {

        binding.btnLogin.setOnClickListener {
            binding.textTitulo.text = "Entrar no App"
            binding.layoutLogin.visibility = View.VISIBLE
            binding.layoutCadastro.visibility = View.GONE
            binding.layoutRecuperarSenha.visibility = View.GONE
            binding.btnLogin.background = resources.getDrawable(R.drawable.bg_swith_left, null)
            binding.btnCadastro.background = null
            binding.btnLogin.setTextColor(resources.getColor(R.color.white, null))
            binding.btnLogin.setTypeface(null, Typeface.BOLD)
            binding.btnCadastro.setTypeface(null, Typeface.NORMAL)
            binding.btnLogin.textSize = 20F
            binding.btnCadastro.textSize = 14F

        }

        binding.btnCadastro.setOnClickListener {
            binding.textTitulo.text = "Criar conta"
            binding.layoutLogin.visibility = View.GONE
            binding.layoutCadastro.visibility = View.VISIBLE
            binding.layoutRecuperarSenha.visibility = View.GONE
            binding.btnCadastro.background = resources.getDrawable(R.drawable.bg_swith_right, null)
            binding.btnLogin.background = null
            binding.btnCadastro.setTextColor(resources.getColor(R.color.white, null))
            binding.btnLogin.setTypeface(null, Typeface.NORMAL)
            binding.btnCadastro.setTypeface(null, Typeface.BOLD)
            binding.btnCadastro.textSize = 20F
            binding.btnLogin.textSize = 14F
        }

        binding.lRebootSenha.setOnClickListener {
            binding.textTitulo.text = "Recuperar senha"
            binding.layoutLogin.visibility = View.GONE
            binding.layoutRecuperarSenha.visibility = View.VISIBLE
        }

        binding.btnEntrar.setOnClickListener {

            binding.progressBar.visibility = View.VISIBLE

            val telaLogin = binding.layoutLogin.visibility
            val telaCadatro = binding.layoutCadastro.visibility
            val telaRecovery = binding.layoutRecuperarSenha.visibility

            if (telaLogin == View.VISIBLE) {
                validarDadosLogin()
            } else if (telaCadatro == View.VISIBLE) {
                validarDadosCadastro()
            } else if (telaRecovery == View.VISIBLE) {
                validarRecoverSenha()
            }

        }
    }

    private fun validarDadosLogin() {
        val email = binding.loginEmail.text.toString().trim()
        val password = binding.loginSenha.text.toString()

        if (!email.isEmpty()) {
            if (!password.isEmpty()) {

                loginUsuario(email, password)

            } else {
                binding.loginSenha.requestFocus()
                binding.loginSenha.error = "Adicione sua senha"
                binding.progressBar.visibility = View.GONE
            }
        } else {
            binding.loginEmail.requestFocus()
            binding.loginEmail.error = "Adicione email"
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun validarDadosCadastro() {

        val nome = binding.cadastroNome.text.toString().trim()
        val email = binding.cadastroEmail.text.toString().trim()
        val password = binding.cadastroSenha.text.toString().trim()
        val confirmPassword = binding.cadastroConfirmarSenha.text.toString().trim()

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!password.isEmpty()) {
                    if (!confirmPassword.isEmpty()) {
                        if (password == confirmPassword) {

                            cadastroUsuario(nome, email, password)

                        } else {
                            binding.cadastroConfirmarSenha.requestFocus()
                            binding.cadastroConfirmarSenha.error = "As senhas não correspondem"
                        }

                    } else {
                        binding.cadastroConfirmarSenha.requestFocus()
                        binding.cadastroConfirmarSenha.error = "Confirme sua senha"
                    }

                } else {
                    binding.cadastroSenha.requestFocus()
                    binding.cadastroSenha.error = "Adicione uma senha"
                }

            } else {
                binding.cadastroEmail.requestFocus()
                binding.cadastroEmail.error = "Adicione seu email"
            }
        } else {
            binding.cadastroNome.requestFocus()
            binding.cadastroNome.error = "Adicione seu nome"
        }

        binding.progressBar.visibility = View.GONE

    }

    private fun validarRecoverSenha() {
        val email = binding.recuperarEmail.text.toString()
        if (!email.isEmpty()) {

            recuperarSenhaUsuario(email)

        } else {
            binding.recuperarEmail.requestFocus()
            binding.recuperarEmail.error = "Adicione o email para recuperar senha"
        }
        binding.progressBar.visibility = View.GONE
    }


    // Cadastro, login e recovery de senha
    private fun loginUsuario(email: String, password: String) {

        binding.progressBar.visibility = View.VISIBLE

        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    exibirMensagem("Bem vindo!")
                    val intent = Intent(this, TelaPrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }.addOnFailureListener { erro ->

                try {
                    throw erro
                } catch (erroCredenciaisInvalidas: FirebaseAuthInvalidCredentialsException) {
                    erroCredenciaisInvalidas.printStackTrace()
                    exibirMensagem("E-mail inválido, digite outro e-mail!")

                } catch (erroUsuarioInvalido: FirebaseAuthInvalidUserException) {
                    erroUsuarioInvalido.printStackTrace()
                    exibirMensagem("Este usuario não existe!")

                } catch (erroEmailInesistente: FirebaseAuthEmailException) {
                    erroEmailInesistente.printStackTrace()
                    exibirMensagem("Este e-mail não existe!")

                } catch (erroSemConexão: FirebaseAuthWebException) {
                    erroSemConexão.printStackTrace()
                    exibirMensagem("sem conexão!")

                } catch (erroFatal: FirebaseAuthException) {
                    erroFatal.printStackTrace()
                    exibirMensagem("Erro ao logar!")
                }
                binding.progressBar.visibility = View.GONE
                exibirMensagem("Erro ao logar!")
            }
    }

    private fun cadastroUsuario(nome: String, email: String, password: String) {

        binding.progressBar.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val idUsuario = task.result.user?.uid
                    if (idUsuario != null) {
                        val usuario = Usuario(
                            idUsuario, nome, email
                        )
                        salvarDadosUsuario(usuario)
                    }
                }
            }.addOnFailureListener { erro ->

                try {
                    throw erro
                } catch (erroCredenciaisInvalidas: FirebaseAuthInvalidCredentialsException) {
                    erroCredenciaisInvalidas.printStackTrace()
                    exibirMensagem("E-mail inválido, digite outro e-mail!")

                } catch (erroEmailExistente: FirebaseAuthUserCollisionException) {
                    erroEmailExistente.printStackTrace()
                    exibirMensagem("Este e-mail esta em uso!")

                } catch (erroSenhaFraca: FirebaseAuthWeakPasswordException) {
                    erroSenhaFraca.printStackTrace()
                    exibirMensagem("Senha fraca, digite uma senha com mais de 6 caracteres!")

                } catch (erroEmailInesistente: FirebaseAuthEmailException) {
                    erroEmailInesistente.printStackTrace()
                    exibirMensagem("Este e-mail não existe!")
                }

                exibirMensagem("Erro ao fazer o cadastro!")
                binding.progressBar.visibility = View.GONE
            }
    }

    private fun salvarDadosUsuario(usuario: Usuario) {

        firestore
            .collection("usuarios")
            .document(usuario.idUsuario)
            .set(usuario)
            .addOnSuccessListener {
                exibirMensagem("Sucesso ao fazer seu cadastro!")

                val intent = Intent(this, TelaPrincipalActivity::class.java)
                intent.putExtra("email", usuario.email)
                startActivity(intent)
                binding.progressBar.visibility = View.GONE
                finish()

            }.addOnFailureListener {
                exibirMensagem("Erro ao salvar dados!")
            }
        binding.progressBar.visibility = View.GONE

    }

    private fun recuperarSenhaUsuario(email: String) {
        binding.progressBar.visibility = View.VISIBLE
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                exibirMensagem("Verifique seu email para atualizar sua senha!")
                binding.textTitulo.text = "Recuperou a senha? entre no App"
                binding.layoutLogin.visibility = View.VISIBLE
                binding.layoutRecuperarSenha.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            } else {
                exibirMensagem("Erro, e-mail inválido!")
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}