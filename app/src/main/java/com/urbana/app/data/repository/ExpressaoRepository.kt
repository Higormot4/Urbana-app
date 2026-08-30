package com.urbana.app.data.repository

import com.urbana.app.data.model.Expressao
import com.urbana.app.data.remote.RetrofitClient

class ExpressaoRepository {
    private val api = RetrofitClient.apiService

    suspend fun getExpressoes(): List<Expressao> = api.getExpressoes()
    suspend fun getCategorias(): List<String> = api.getCategorias()
}