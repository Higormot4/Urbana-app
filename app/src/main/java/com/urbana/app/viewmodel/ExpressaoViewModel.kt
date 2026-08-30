package com.urbana.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbana.app.R
import com.urbana.app.data.model.Expressao
import com.urbana.app.data.repository.ExpressaoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpressaoViewModel : ViewModel() {
    private val repository = ExpressaoRepository()

    private val _expressoes = MutableStateFlow<List<Expressao>>(emptyList())
    val expressoes: StateFlow<List<Expressao>> = _expressoes

    private val _categorias = MutableStateFlow<List<String>>(emptyList())
    val categorias: StateFlow<List<String>> = _categorias

    private val _selectedCategoria = MutableStateFlow<String?>(null)
    val selectedCategoria: StateFlow<String?> = _selectedCategoria

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Tenta carregar do backend
                val expressoesApi = repository.getExpressoes()
                _expressoes.value = expressoesApi
                _categorias.value = repository.getCategorias()
                _error.value = null
            } catch (e: Exception) {
                // Se falhar, usa dados locais com imagens do drawable
                _expressoes.value = getDadosLocais()
                _categorias.value = listOf(
                    "Grafite", "Arte de Rua", "Arquitetura Orgânica",
                    "Escultura Urbana", "Performance Urbana"
                )
                _error.value = "Usando dados locais"
            }
            _isLoading.value = false
        }
    }

    fun filtrarPorCategoria(categoria: String?) {
        _selectedCategoria.value = categoria
    }

    private fun getDadosLocais(): List<Expressao> {
        return listOf(
            Expressao(
                id = 1,
                nome = "OSGEMEOS - Gigantes Urbanos",
                categoria = "Grafite",
                cidade = "São Paulo",
                artista = "Otavio e Gustavo Pandolfo",
                descricao = "Os irmãos Otavio e Gustavo Pandolfo, conhecidos como OSGEMEOS, são famosos mundialmente por seus personagens amarelos característicos e grafites coloridos que transformam paredes em obras de arte.",
                imagemUrl = "android.resource://com.urbana.app/${R.drawable.gemeos}"
            ),
            Expressao(
                id = 2,
                nome = "Beco do Batman",
                categoria = "Arte de Rua",
                cidade = "São Paulo",
                artista = "Diversos Artistas",
                descricao = "O Beco do Batman, na Vila Madalena, é um dos pontos turísticos mais famosos de São Paulo, com grafites em constante transformação.",
                imagemUrl = "android.resource://com.urbana.app/${R.drawable.becobatman}"
            ),
            Expressao(
                id = 3,
                nome = "Museu Guggenheim Bilbao",
                categoria = "Arquitetura Orgânica",
                cidade = "Bilbao",
                artista = "Frank Gehry",
                descricao = "O Museu Guggenheim de Bilbao é uma obra-prima da arquitetura orgânica, projetado por Frank Gehry, com estrutura de titânio ondulante.",
                imagemUrl = "android.resource://com.urbana.app/${R.drawable.guggenheim}"
            ),
            )
    }
}