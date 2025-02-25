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
    val pruebasFiltradas = rememberSaveable(searchView) {
        pruebas.filter { it.nombre.contains(searchView, ignoreCase = true) }
    }

    // Agrupamos por categorias
    val pruebasAgrupadasCategoria = pruebasFiltradas.groupBy { it.categoriaPrueba }

    // Organizmao de manera vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // BUSCADOR
        OutlinedTextField(
            value = searchView,
            // Se actualiza el texto del buscador
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

        LazyColumn {

            pruebasAgrupadasCategoria.forEach { (categoria, pruebas) ->
                item {
                    // Cada item que vaya mostrando su categoria en un texto y cuando buscamos en el buscador siguen apareciendo encima de cada card view
                    Text(
                        text = categoria.name.replace("_",""), // Reemplazo las que tengo con barra baja por nada
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                // Iteramos sobre cada uno para que nos lo muestre desde una lista
                items(pruebas) { prueba ->
                    PruebaItem(prueba, { pruebaSelected(prueba.nombre) })
                }
            }


        }
    }
}

