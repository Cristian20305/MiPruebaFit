package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mipruebafit.Data.model.calcularNota

@Composable
fun NotasPruebas(edad: Int, generoUsuario: String, nombrePrueba: String) {
    var resultado by rememberSaveable { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }
    val dialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = resultado,
            onValueChange = { resultado = it },
            label = { Text("Introduce tu resultado") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val resultadoNota = resultado.toFloatOrNull()
                if (resultadoNota != null) {
                    // Debug para verificar los valores pasados a calcularNota
                    println("Calculando nota para: Prueba=$nombrePrueba, Resultado=$resultadoNota, Edad=$edad, Género=$generoUsuario")

                    nota = calcularNota(nombrePrueba, resultadoNota, edad, generoUsuario)

                    println("Nota calculada: $nota")

                    dialog.value = true
                } else {
                    nota = "Entrada inválida"
                }
            }
        ) {
            Text("Calcular la nota")
        }
    }

    if (dialog.value) {
        AlertDialog(
            onDismissRequest = { dialog.value = false },
            confirmButton = {
                Button(onClick = { dialog.value = false }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Resultado") },
            text = { Text("Tu nota es: $nota") }
        )
    }
}