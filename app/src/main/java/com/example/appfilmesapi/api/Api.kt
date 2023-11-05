package com.example.appfilmesapi.api

import com.example.appfilmesapi.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {


    @GET("/filmes")
    fun listaCategorias(): Call<Categorias>
}