package com.urbana.app.data.remote

import com.urbana.app.data.model.Expressao
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("expressoes")
    suspend fun getExpressoes(): List<Expressao>

    @GET("expressoes/{id}")
    suspend fun getExpressao(@Path("id") id: Int): Expressao

    @GET("categorias")
    suspend fun getCategorias(): List<String>
}