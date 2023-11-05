package com.example.appfilmesapi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilmesapi.R
import com.example.appfilmesapi.activitys.DetalhesActivity
import com.example.appfilmesapi.databinding.ItemFilmeBinding
import com.example.appfilmesapi.model.Filme
import com.squareup.picasso.Picasso
import retrofit2.Retrofit

class FilmeAdapter(
    private val listaFilme: MutableList<Filme>,
) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    inner class FilmeViewHolder(private val binding: ItemFilmeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgCapa = binding.imgCapa
        fun bind(lista: Filme) {

            Picasso
                .get()
                .load(lista.capa)
                .placeholder(R.drawable.placeholder)
                .into(imgCapa)

            imgCapa.setOnClickListener {
                val intent = Intent(imgCapa.context, DetalhesActivity::class.java)
                intent.putExtra("url_imagem", lista.capa)
                intent.putExtra("nome", lista.titulo)
                intent.putExtra("descricao", lista.descricao)
                intent.putExtra("elenco", lista.elenco)
                imgCapa.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = ItemFilmeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmeViewHolder(itemLista)
    }

    override fun getItemCount(): Int {
        return listaFilme.size
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val lista = listaFilme[position]
        holder.bind(lista)
    }
}