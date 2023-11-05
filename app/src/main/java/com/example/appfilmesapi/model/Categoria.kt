package com.example.appfilmesapi.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("capas") val filmes: MutableList<Filme> = mutableListOf()
)

data class Filme(
    @SerializedName("url_imagem") val capa: String? = null,
    @SerializedName("nome") val titulo: String? = null,
    @SerializedName("descricao") val descricao: String? = null,
    @SerializedName("elenco") val elenco: String? = null,
    @SerializedName("id") val id: Int? = 0
)

data class Categorias(
    @SerializedName("categoria")
    val categorias: MutableList<Categoria> = mutableListOf()
)