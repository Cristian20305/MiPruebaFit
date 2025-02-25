package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

    // Organizmao de manera vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Lista de Pruebas para esta edad $edadUsuario años",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            // Iteramos sobre cada uno para que nos lo muestre desde una lista
            items(pruebas) { prueba ->
                PruebaItem(prueba, pruebaSelected)
            }
        }
    }
}

