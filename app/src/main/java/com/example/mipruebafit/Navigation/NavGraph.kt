package com.example.mipruebafit.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mipruebafit.ui.screens.LoginScreen
import com.example.mipruebafit.ui.screens.UserScreen
import com.example.mipruebafit.ui.screens.PruebasScreen

@Composable
fun NavigationWrapper(modifier: Modifier) {

    // Objeto que controla la navegación
    val navController = rememberNavController()

    // NavHost con la ruta de inicio definida como LoginScreenRoute
    NavHost(
        navController = navController,
        startDestination = LoginScreenRoute
    ) {
        // Pantalla de Login: al presionar Ingresar navega a la pantalla de Usuario
        composable<LoginScreenRoute> {
            LoginScreen {
                navController.navigate(UserScreenRoute)
            }
        }
        // Pantalla de Usuario: al continuar, navega a la pantalla de Pruebas
        composable<UserScreenRoute> {
            UserScreen {
                navController.navigate(PruebasScreenRoute)
            }
        }
        // Pantalla de Pruebas: mas adelante la utilizmaos
        composable<PruebasScreenRoute> {
            PruebasScreen { pruebaId ->
                // De momento regreso a la pantalla anterior:
                navController.popBackStack()
            }
        }
    }
}
