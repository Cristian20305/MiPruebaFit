package com.example.mipruebafit.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

//Pantalla de login que nos lleva a la siguiente pantalla que es UserScreen tras inicar sesion

@Composable
fun LoginScreen(loginSuccess: () -> Unit) {

    //Variables de estado para almacenar el nombre de usuario y contraseña
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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

        Button(
            onClick = { loginSuccess() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}