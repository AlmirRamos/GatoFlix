package com.example.appfilmesapi.activitys

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appfilmesapi.R
import com.example.appfilmesapi.databinding.ActivityDetalhesBinding
import com.squareup.picasso.Picasso

class DetalhesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //window.statusBarColor = Color.parseColor(#000000)

        val bundle = intent.extras

        val capa = bundle?.getString("url_imagem")
        val titulo = bundle?.getString("nome")
        val descricao = bundle?.getString("descricao")
        val elenco = bundle?.getString("elenco")

        Picasso.get().load(capa).placeholder(R.drawable.placeholder).into(binding.imageCapaDetalhes)
        binding.textTituloDetalhes.text = titulo
        binding.textDescricaoDetalhes.text = "Descrição: $descricao"
        binding.textElencoDetalhes.text = "Elenco: $elenco"
    }
}