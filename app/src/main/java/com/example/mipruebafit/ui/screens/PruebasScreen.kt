package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mipruebafit.Data.model.Prueba
import com.example.mipruebafit.Data.repository.PruebasRepository
import com.example.mipruebafit.R
import com.example.mipruebafit.ui.components.PruebaItem

@Composable
fun PruebasScreen(pruebaSelected: (String) -> Unit, edadUsuario: Int) {

    // Lista de pruebas que se mostraran en la pantalla depende de la edad
    val pruebas = PruebasRepository.obtenerPruebasPorEdad(edadUsuario)

    // Estado para el texto del buscador
    var searchView by rememberSaveable { mutableStateOf("") }

    // Lista de las pruebas filtradas por el texto
    val pruebasFiltradas = remember {
        derivedStateOf {
            pruebas.filter {
                it.nombre.contains(
                    searchView,
                    ignoreCase = true
                )
            }
        }
    }

    // Agrupamos por categorias
    val pruebasAgrupadasCategoria = pruebasFiltradas.value.groupBy { it.categoriaPrueba }

    // Estado para la categoria seleccionada en el dropdown
    var categoriaSeleccionada by rememberSaveable { mutableStateOf<String?>(null) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // BUSCADOR
        OutlinedTextField(
            value = searchView,
            onValueChange = { searchView = it },
            label = { Text("Buscar pruebas") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Lista de Pruebas para esta edad $edadUsuario años",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para filtrar por CATEGORIA
        Box {
            Button(onClick = { expanded = true }) {
                Text(text = categoriaSeleccionada ?: "Selecciona una categoría")
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Todas") },
                    onClick = {
                        categoriaSeleccionada = null
                        expanded = false
                    }
                )
                pruebasAgrupadasCategoria.keys.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria.name.replace("_", "")) },
                        onClick = {
                            categoriaSeleccionada = categoria.name
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostramos las pruebas agrupadas por categoría
        LazyColumn {
            pruebasAgrupadasCategoria.forEach { (categoria, pruebas) ->
                if (categoriaSeleccionada == null || categoria.name == categoriaSeleccionada) {
                    item {
                        // Mostramos la categoría encima de las pruebas
                        Text(
                            text = categoria.name.replace("_", ""),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    // Iteramos sobre cada prueba de la categoría seleccionada y la mostramos
                    items(pruebas) { prueba ->
                        PruebaItem(prueba, { pruebaSelected(prueba.nombre) })
                    }
                }
            }
        }
    }
}
