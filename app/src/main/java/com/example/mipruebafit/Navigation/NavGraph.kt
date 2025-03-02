package com.example.mipruebafit.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mipruebafit.ui.screens.NotasPruebas
import com.example.mipruebafit.ui.screens.ChangePasswordScreen
import com.example.mipruebafit.ui.screens.LoginScreen
import com.example.mipruebafit.ui.screens.UserScreen
import com.example.mipruebafit.ui.screens.PruebasScreen

@Composable
fun NavigationWrapper(modifier: Modifier) {

    // Objeto que controla la navegación
    val navController = rememberNavController()
    var edadUsuario by rememberSaveable { mutableStateOf(0) }
    var generoUsuario by rememberSaveable { mutableStateOf("Masculino") }

    NavHost(navController = navController, startDestination = LoginScreenRoute) {
        composable<LoginScreenRoute> {
            LoginScreen(
                loginSuccess = { navController.navigate(UserScreenRoute) },
                onForgotPassword = { navController.navigate(ChangePasswordScreenRoute) }
            )
        }
        // Pantalla de Usuario: al continuar, navega a la pantalla de Pruebas
        composable<UserScreenRoute> {
            UserScreen { edad ->
                edadUsuario = edad //Guardo la edad
                navController.navigate(PruebasScreenRoute(edadUsuario)) //Navegamos pasadno la edad
            }
        }

        // Pantalla de pruebas
        composable<PruebasScreenRoute> {
            PruebasScreen(
                pruebaSelected = { nombrePrueba ->
                    navController.navigate(NotasScreenRoute(edadUsuario, generoUsuario,
                        nombrePrueba.nombrePrueba
                    ))
                },
                edadUsuario = edadUsuario
            )
        }
        //Pantalla de notas pasando los argumentos necesarios para calcular esa nota
        composable<NotasScreenRoute> { backStackEntry ->
            val args = backStackEntry.arguments
            val edad = args?.getInt("edadUsuario") ?: 0
            val genero = args?.getString("generoUsuario") ?: "Masculino"
            val nombrePrueba = args?.getString("nombrePrueba") ?: "Abdominales"

            NotasPruebas(
                edad = edad,
                generoUsuario = genero,
                nombrePrueba = nombrePrueba
            )
        }
        // Navegacion para cambiar la contraseña
        composable<ChangePasswordScreenRoute> {
            ChangePasswordScreen { navController.popBackStack() }
        }
    }
}
