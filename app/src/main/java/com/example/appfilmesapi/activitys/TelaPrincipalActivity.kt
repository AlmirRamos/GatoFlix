package com.example.appfilmesapi.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfilmesapi.R
import com.example.appfilmesapi.adapter.CategoriaAdapter
import com.example.appfilmesapi.databinding.ActivityTelaPrincipalBinding
import com.example.appfilmesapi.model.Categoria
import com.example.appfilmesapi.model.Filme
import com.example.appfilmesapi.utill.exibirMensagem
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipalActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaPrincipalBinding.inflate(layoutInflater)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    private lateinit var categoriaAdapter: CategoriaAdapter
    private val listaCategoria: MutableList<Categoria> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configClicks()
        configAdapterCategoria()

    }

    private fun configAdapterCategoria() {
        val rvFilmes = binding.rvFilmes
        rvFilmes.layoutManager = LinearLayoutManager(this)
        rvFilmes.setHasFixedSize(true)
        categoriaAdapter = CategoriaAdapter(listaCategoria)
        rvFilmes.adapter = categoriaAdapter

        getCategorias()
    }

    private fun configClicks() {
        binding.textSair.setOnClickListener{
            auth.signOut()
            startActivity(Intent(applicationContext, AuthActivity::class.java))
            finish()
            exibirMensagem("Usuario deslogado com sucesso!")
        }
    }

    private fun getCategorias(){
        val categoria1 = Categoria("Filmes populares")
        listaCategoria.add(categoria1)

        val categoria2 = Categoria("Filmes em Alta")
        listaCategoria.add(categoria2)

        val categoria3 = Categoria("Filmes de Ação")
        listaCategoria.add(categoria3)

        val categoria4 = Categoria("Filmes de Terror")
        listaCategoria.add(categoria4)
    }

}