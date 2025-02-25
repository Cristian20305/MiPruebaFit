package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

//Pantalla de usuario para ingresar los datos de esa persona como edas, poeso, altura y genero.
//Tambien calculamos el IMC en un dialog
@Composable
fun UserScreen(onContinue: (Int) -> Unit) {

    // Variables de estado para almacenar la edad, peso y altura del usuario.
    var edad by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }

    //Variables de estado para contorlar generos
    var masculino by remember { mutableStateOf(true) } // Predeterminado masculino
    var femenino by remember { mutableStateOf(false) }

    // Variables para controlar la visibilidad del dialogo del IMC
    var showDialog by remember { mutableStateOf(false) }
    var imcResultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Datos del Usuario", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para ingresar la edad
        TextField(
            value = edad,  //Valor actual de edad
            onValueChange = { edad = it }, //Actualiza edad
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //Teclado para solo numeros
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para ingresar el peso en kilogramos.
        TextField(
            value = peso, // Valor actual de peso
            onValueChange = { peso = it }, // Actualiza peso
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //Teclado para solo numeros
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Campo de texto para ingresar la altura en centimetros
        TextField(
            value = altura, //Valor actual de altura
            onValueChange = { altura = it }, //Actualiza altura
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //Teclado para solo numeros
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // GENERO
        Text(text = "Género", style = MaterialTheme.typography.bodyLarge)

        // Row para que los CheckBox salgan todo en un misma linea
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = masculino, onCheckedChange = {
                masculino = it
                femenino = !it  // Si se marca masculino, desmarca femenino
            })
            Text("Masculino", modifier = Modifier.clickable {
                masculino = true
                femenino = false
            })

            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre las opciones

            Checkbox(checked = femenino, onCheckedChange = {
                femenino = it
                masculino = !it  // Si se marca femenino, desmarca masculino
            })
            Text("Femenino", modifier = Modifier.clickable {
                femenino = true
                masculino = false
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BOTON para calcular el IMC
        Button(
            onClick = {
                val pesoFloat = peso.toFloatOrNull()
                val alturaFloat = altura.toFloatOrNull()?.div(100) // Convertir cm a metros

                if (pesoFloat != null && alturaFloat != null && alturaFloat > 0) {
                    val imc = pesoFloat / (alturaFloat * alturaFloat)
                    val categoria = when {
                        imc < 18.5 -> "Bajo peso"
                        imc in 18.5..24.9 -> "Peso normal"
                        imc in 25.0..29.9 -> "Sobrepeso"
                        else -> "Obesidad"
                    }
                    imcResultado = "Tu IMC es: %.2f\nCategoria: $categoria".format(imc)
                    showDialog = true
                } else {
                    imcResultado = "Por favor, introduce un peso y altura validos."
                    showDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular IMC")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BOTON para continuar
        Button(
            onClick = {
                val edadInt = edad.toIntOrNull() ?: 0 // Convertimos la edad a Int
                onContinue(edadInt) // Pasamos la edad
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }

    }

    // Dialog para mostrar el resultado del IMC
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Resultado del IMC") },
            text = { Text(imcResultado) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

