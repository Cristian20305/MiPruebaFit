package com.example.mipruebafit.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mipruebafit.Data.repository.UserPreferences

//Pantalla de login que nos lleva a la siguiente pantalla que es UserScreen tras inicar sesion

@Composable
fun LoginScreen(loginSuccess: () -> Unit, onForgotPassword: () -> Unit) {

    // Coger el contesto actual para acceder al dataStore
    val context = LocalContext.current
    // En una instancia manejamos la contraseña UserPreferences
    val userPreferences = remember { UserPreferences(context) }

    //Variables de estado para almacenar el nombre de usuario y contraseña
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var savedPassword by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }


    LaunchedEffect(Unit) {
        userPreferences.password.collect { savedPassword = it }
    }

    //Layout principal en forma de columna
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        //Campo de texto para ingresar el nombre de usuario
        TextField(
            value = username,  //Valor actual de campo
            onValueChange = { username = it },  //Actualiza el valor de userName cuando se escribe
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Campo de texto para ingresar la contraseña
        TextField(
            value = password,   //Valor actual de campo
            onValueChange = { password = it }, //Actualiza el valor de password cuando se escribe
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(), // Enmascara el texto ingresado para proteger la contraseña
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Sale el teclado para ingresar la contraseña
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton de olvidar contraseña que nos lleva a otra actividad para cambiarla y compara la contraseña que ponemos con la guardada
        Button(
            onClick = {
                if (password == savedPassword) {
                    loginSuccess()
                } else {
                    message = "Contraseña incorrecta"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { onForgotPassword() }) {
            Text("¿Olvidaste tu contraseña?")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (message.isNotEmpty()) {
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
    }

}