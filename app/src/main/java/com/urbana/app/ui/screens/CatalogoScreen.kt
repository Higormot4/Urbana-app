package com.urbana.app.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.urbana.app.ui.components.ExpressaoCard
import com.urbana.app.viewmodel.ExpressaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    expressaoViewModel: ExpressaoViewModel,
    onItemClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val expressoes = expressaoViewModel.expressoes.collectAsState()
    val categorias = expressaoViewModel.categorias.collectAsState()
    val selectedCategoria = expressaoViewModel.selectedCategoria.collectAsState()
    val isLoading = expressaoViewModel.isLoading.collectAsState()

    val filteredExpressoes = if (selectedCategoria.value == null) {
        expressoes.value
    } else {
        expressoes.value.filter { it.categoria == selectedCategoria.value }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Filtros de categoria
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategoria.value == null,
                    onClick = { expressaoViewModel.filtrarPorCategoria(null) },
                    label = { Text("Todas") }
                )

                categorias.value.forEach { categoria ->
                    FilterChip(
                        selected = selectedCategoria.value == categoria,
                        onClick = { expressaoViewModel.filtrarPorCategoria(categoria) },
                        label = { Text(categoria) }
                    )
                }
            }

            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(filteredExpressoes) { expressao ->
                        ExpressaoCard(
                            expressao = expressao,
                            onClick = { onItemClick(expressao.id) }
                        )
                    }
                }
            }
        }
    }
}