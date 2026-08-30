package com.urbana.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.urbana.app.viewmodel.ExpressaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(
    expressaoId: Int,
    expressaoViewModel: ExpressaoViewModel,
    onBack: () -> Unit
) {
    val expressoes = expressaoViewModel.expressoes.collectAsState()
    val expressao = expressoes.value.find { it.id == expressaoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (expressao != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Imagem
                if (expressao.imagemUrl.startsWith("android.resource://")) {
                    // Imagem local
                    val resourceId = expressao.imagemUrl.substringAfterLast("/").toIntOrNull()
                    if (resourceId != null) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = expressao.nome,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    // Imagem da internet
                    SubcomposeAsyncImage(
                        model = expressao.imagemUrl,
                        contentDescription = expressao.nome,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .background(Color.Gray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Sem imagem",
                                    color = Color.White
                                )
                            }
                        }
                    )
                }

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = expressao.nome,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = expressao.categoria,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Cidade: ${expressao.cidade}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Artista: ${expressao.artista}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Descrição",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = expressao.descricao,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}