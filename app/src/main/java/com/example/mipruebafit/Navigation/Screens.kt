package com.example.mipruebafit.Navigation

import kotlinx.serialization.Serializable

// Ruta para la pantalla de Login
@Serializable
object LoginScreenRoute

// Ruta para la pantalla de Usuario
@Serializable
object UserScreenRoute

// Ruta para la pantalla de Pruebas y pasamos la edad a esta desde UserScreenRoute
@Serializable
data class PruebasScreenRoute(val edadUsuario:Int)
