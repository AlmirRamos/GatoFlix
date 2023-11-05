package com.example.appfilmesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilmesapi.R
import com.example.appfilmesapi.databinding.ItemFilmeBinding
import com.example.appfilmesapi.model.Filme

class FilmeAdapter(
    private val listaFilme: MutableList<Filme>
): RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>(){

    inner class FilmeViewHolder(private val binding: ItemFilmeBinding) : RecyclerView.ViewHolder(binding.root){

        val imgCapa = binding.imgCapa
        fun bind(lista: Filme){
            imgCapa.setImageResource(R.drawable.placeholder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = ItemFilmeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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