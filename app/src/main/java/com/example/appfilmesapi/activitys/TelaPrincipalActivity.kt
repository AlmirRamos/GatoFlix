package com.example.appfilmesapi.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appfilmesapi.R
import com.example.appfilmesapi.databinding.ActivityTelaPrincipalBinding
import com.example.appfilmesapi.utill.exibirMensagem
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipalActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaPrincipalBinding.inflate(layoutInflater)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configClicks()
    }

    private fun configClicks() {
        binding.textSair.setOnClickListener{
            auth.signOut()
            startActivity(Intent(applicationContext, AuthActivity::class.java))
            finish()
            exibirMensagem("Usuario deslogado com sucesso!")
        }
    }
}