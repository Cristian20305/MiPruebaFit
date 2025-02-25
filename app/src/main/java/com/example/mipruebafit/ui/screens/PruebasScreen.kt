package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PruebasScreen(pruebaSelected: (String) -> Unit, edadUsuario: Int) {

    // Lista de pruebas que se mostraran en la pantalla depende de la edad
    val pruebas = obtenerPruebasPorEdad(edadUsuario)

    // Organizmao de manera vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Lista de Pruebas para esta edad $edadUsuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            // Iteramos sobre cada uno para que nos lo muestre desde una lista
            items(pruebas) { prueba ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { pruebaSelected(prueba) } // Llama a la funcion onPruebaSelected pasando la prueba seleccionada
                ) {
                    Text(
                        text = prueba,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

// Para determinar que pruebas mostrar segun la edad del excel <=12 , 13, 14, 15, >=16
fun obtenerPruebasPorEdad(edad: Int): List<String> {
    return when {
        edad <= 12 -> listOf("Abdominales", "Flexibilidad", "Test de Cooper")
        edad == 13 -> listOf("Abdominales", "Flexibilidad", "Test de Cooper")
        edad == 14 -> listOf("Abdominales", "Flexibilidad", "Test de Cooper", "Velocidad")
        edad == 15 -> listOf(
            "Abdominales",
            "Flexibilidad",
            "Test de Cooper",
            "Velocidad",
            "Lanzamiento Balón"
        )

        else -> listOf(
            "Abdominales",
            "Flexibilidad",
            "Test de Cooper",
            "Velocidad",
            "Lanzamiento Balón"
        ) // Para 16 o más
    }
}