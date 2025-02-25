package com.example.mipruebafit.Data.repository

import com.example.mipruebafit.Data.model.CategoriaPrueba
import com.example.mipruebafit.Data.model.Prueba
import com.example.mipruebafit.R

//Creamos un objeto de pruebas para matener mas limpio y ordenador el codigo tambien para añadir en un futuro cualquier cosa que necesitemos otra lista diferente por ejemplo
object PruebasRepository {

    // Para determinar que pruebas mostrar segun la edad del excel <=12 , 13, 14, 15, >=16
    fun obtenerPruebasPorEdad(edad: Int): List<Prueba> {
        return when {
            edad <= 12 -> listOf(
                Prueba(
                    "Abdominales",
                    R.drawable.abdominales,
                    "https://altorendimiento.com/prueba-de-abdominales/#:~:text=Como%20llevar%20a%20cabo%20esta%20prueba&text=Comienza%20cada%20abdominal%20con%20la,abdominales%20completadas%20en%2030%20segundos",
                    categoriaPrueba = CategoriaPrueba.FUERZA_MUSCULAR
                ),
                Prueba(
                    "Flexibilidad",
                    R.drawable.flexibilidad,
                    "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad",
                    CategoriaPrueba.FLEXIBILIDAD
                ),
                Prueba(
                    "Test de Cooper",
                    R.drawable.cooper,
                    "https://www.palabraderunner.com/test-de-cooper/",
                    CategoriaPrueba.RESISTENCIA
                )
            )

            edad == 13 -> listOf(
                Prueba(
                    "Abdominales",
                    R.drawable.abdominales,
                    "https://altorendimiento.com/prueba-de-abdominales/#:~:text=Como%20llevar%20a%20cabo%20esta%20prueba&text=Comienza%20cada%20abdominal%20con%20la,abdominales%20completadas%20en%2030%20segundos",
                    CategoriaPrueba.FUERZA_MUSCULAR
                ),
                Prueba(
                    "Flexibilidad",
                    R.drawable.flexibilidad,
                    "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad",
                    categoriaPrueba = CategoriaPrueba.FLEXIBILIDAD
                ),
                Prueba(
                    "Test de Cooper",
                    R.drawable.cooper,
                    "https://www.palabraderunner.com/test-de-cooper/",
                    categoriaPrueba = CategoriaPrueba.RESISTENCIA
                )
            )

            edad == 14 -> listOf(
                Prueba(
                    "Abdominales",
                    R.drawable.abdominales,
                    "https://altorendimiento.com/prueba-de-abdominales/#:~:text=Como%20llevar%20a%20cabo%20esta%20prueba&text=Comienza%20cada%20abdominal%20con%20la,abdominales%20completadas%20en%2030%20segundos",
                    categoriaPrueba = CategoriaPrueba.FUERZA_MUSCULAR
                ),
                Prueba(
                    "Flexibilidad",
                    R.drawable.flexibilidad,
                    "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad",
                    CategoriaPrueba.FLEXIBILIDAD
                ),
                Prueba(
                    "Test de Cooper",
                    R.drawable.cooper,
                    "https://www.palabraderunner.com/test-de-cooper/",
                    categoriaPrueba = CategoriaPrueba.RESISTENCIA
                ),
                Prueba(
                    "Velocidad",
                    R.drawable.velocidad,
                    "https://pruebasdeportivas.com/blog/carreras-de-velocidad",
                    categoriaPrueba = CategoriaPrueba.VELOCIDAD
                )
            )

            edad == 15 -> listOf(
                Prueba(
                    "Abdominales",
                    R.drawable.abdominales,
                    "https://altorendimiento.com/prueba-de-abdominales/#:~:text=Como%20llevar%20a%20cabo%20esta%20prueba&text=Comienza%20cada%20abdominal%20con%20la,abdominales%20completadas%20en%2030%20segundos",
                    categoriaPrueba = CategoriaPrueba.FUERZA_MUSCULAR
                ),
                Prueba(
                    "Flexibilidad",
                    R.drawable.flexibilidad,
                    "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad",
                    CategoriaPrueba.FLEXIBILIDAD
                ),
                Prueba(
                    "Test de Cooper",
                    R.drawable.cooper,
                    "https://www.palabraderunner.com/test-de-cooper/",
                    CategoriaPrueba.RESISTENCIA
                ),
                Prueba(
                    "Velocidad",
                    R.drawable.velocidad,
                    "https://pruebasdeportivas.com/blog/carreras-de-velocidad",
                    CategoriaPrueba.VELOCIDAD
                ),
                Prueba(
                    "Lanzamiento Balón",
                    R.drawable.balon,
                    "https://www.jowyoriginals.com/test-lanzamiento-balon-medicinal/",
                    CategoriaPrueba.COORDINACION
                )
            )

            else -> listOf(
                Prueba(
                    "Abdominales",
                    R.drawable.abdominales,
                    "https://altorendimiento.com/prueba-de-abdominales/#:~:text=Como%20llevar%20a%20cabo%20esta%20prueba&text=Comienza%20cada%20abdominal%20con%20la,abdominales%20completadas%20en%2030%20segundos",
                    CategoriaPrueba.FUERZA_MUSCULAR
                ),
                Prueba(
                    "Flexibilidad",
                    R.drawable.flexibilidad,
                    "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad",
                    CategoriaPrueba.FLEXIBILIDAD
                ),
                Prueba(
                    "Test de Cooper",
                    R.drawable.cooper,
                    "https://www.palabraderunner.com/test-de-cooper/",
                    CategoriaPrueba.RESISTENCIA
                ),
                Prueba(
                    "Velocidad",
                    R.drawable.velocidad,
                    "https://pruebasdeportivas.com/blog/carreras-de-velocidad",
                    CategoriaPrueba.VELOCIDAD
                ),
                Prueba(
                    "Lanzamiento Balón",
                    R.drawable.balon,
                    "https://www.jowyoriginals.com/test-lanzamiento-balon-medicinal/",
                    CategoriaPrueba.COORDINACION
                )
            )
        }
    }
}