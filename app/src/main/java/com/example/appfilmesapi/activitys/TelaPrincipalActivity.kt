package com.example.appfilmesapi.activitys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfilmesapi.R
import com.example.appfilmesapi.adapter.CategoriaAdapter
import com.example.appfilmesapi.api.Api
import com.example.appfilmesapi.databinding.ActivityTelaPrincipalBinding
import com.example.appfilmesapi.model.Categoria
import com.example.appfilmesapi.model.Categorias
import com.example.appfilmesapi.model.Filme
import com.example.appfilmesapi.utill.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        configRetrofit()

    }

    private fun configRetrofit() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(Api::class.java)

        retrofit.listaCategorias().enqueue(object : Callback<Categorias>{

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if (response.code() == 200){
                    response.body()?.let {
                        categoriaAdapter.listaCategorias.addAll(it.categorias)
                        categoriaAdapter.notifyDataSetChanged()

                        binding.viewRecarregando.visibility = View.GONE
                        binding.progressBarRecarregando.visibility = View.GONE
                        binding.textRecarregando.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun configAdapterCategoria() {

        val rvFilmes = binding.rvFilmes
        rvFilmes.layoutManager = LinearLayoutManager(this)
        rvFilmes.setHasFixedSize(true)
        categoriaAdapter = CategoriaAdapter(listaCategoria)
        rvFilmes.adapter = categoriaAdapter
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