package com.example.mipruebafit.Data.model

// Modelo de datos para una prueba para nuestro RECYCLERVIEW
data class Prueba(
    val nombre: String,
    val imagen: Int,
    val enlace: String,
    val categoriaPrueba: CategoriaPrueba //Añadimos la clase enumerada para que todas las pruebas tegan una categoria
)
