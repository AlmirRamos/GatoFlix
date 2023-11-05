package com.example.appfilmesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appfilmesapi.databinding.ItemCategioriaBinding
import com.example.appfilmesapi.model.Categoria
import com.example.appfilmesapi.model.Filme

class CategoriaAdapter(
    private val listaCategorias: MutableList<Categoria>

): Adapter<CategoriaAdapter.CategoriaViewHolder>(){

    inner class CategoriaViewHolder(private val binding: ItemCategioriaBinding) : ViewHolder(binding.root){

        val titulo = binding.textTitulo
        val rvCategoriaFilmes = binding.rvCategoriaFilmes

        fun bind(listaCategoria: Categoria){
            titulo.text = listaCategoria.titulo
            rvCategoriaFilmes.adapter = FilmeAdapter(listaCategoria.filmes)
            rvCategoriaFilmes.layoutManager = LinearLayoutManager(titulo.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemLista = ItemCategioriaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriaViewHolder(itemLista)
    }

    override fun getItemCount(): Int {
        return listaCategorias.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val listaCategoria = listaCategorias[position]
        holder.bind(listaCategoria)

    }
}