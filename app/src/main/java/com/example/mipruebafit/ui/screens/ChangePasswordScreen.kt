package com.example.mipruebafit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import com.example.mipruebafit.Data.repository.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(onBack: () -> Unit) {

    // Cogemos el contextp
    val context = LocalContext.current

    // En una instancia manejamos la contraseña UserPreferences
    val userPreferences = remember { UserPreferences(context) }

    // Creamos un CoroutineScope para ejecutar funciones suspendidas
    val scope = rememberCoroutineScope()

    // Variables de estado para almacenar los valores ingresados por el usuario
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cambiar Contraseña", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Nueva Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Actualizar la contraseña
        Button(
            onClick = {
                // Si la nueva contraseña no esta vacia y coincida con la de confirmacion
                if (newPassword == confirmPassword && newPassword.isNotEmpty()) {
                    scope.launch {
                        userPreferences.updatePassword(newPassword) // Guardamos la nueva contraseña
                        message = "Contraseña actualizada correctamente" // Mostramos mensaje de que se ha actualizado
                    }
                } else {
                    message = "Las contraseñas no coinciden o están vacías"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Contraseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Volvemos a la pantalla anterior para que el usuario pueda registrarse
        Button(onClick = { onBack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje para ver si hay algun error
        if (message.isNotEmpty()) {
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
    }
}
