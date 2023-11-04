package com.example.appfilmesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appfilmesapi.databinding.ItemCategioriaBinding
import com.example.appfilmesapi.model.Categoria

class CategoriaAdapter(
    private val listaCategorias: MutableList<Categoria>
): Adapter<CategoriaAdapter.CategoriaViewHolder>(){

    inner class CategoriaViewHolder(private val binding: ItemCategioriaBinding) : ViewHolder(binding.root){

        val titulo = binding.textTitulo

        fun bind(lista: Categoria){
            titulo.text = lista.titulo
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
        val lista = listaCategorias[position]
        holder.bind(lista)
    }
}