#  **Documentación del Proyecto MiPruebaFit**  

## 📖 **Descripción del Proyecto**  
**MiPruebaFit** es una aplicación desarrollada en **Jetpack Compose** que permite a los usuarios consultar y evaluar su desempeño en diversas **pruebas físicas** según su edad y género.  

El objetivo principal es proporcionar una herramienta intuitiva y eficiente para calcular y visualizar las notas basadas en baremos predefinidos.  

---

##  **Funcionalidades Principales**  
✔️ **Inicio de Sesión:** Permite a los usuarios ingresar con su contraseña guardada.  
✔️ **Gestión de Datos del Usuario:** Captura edad, género, peso y altura.  
✔️ **Selección de Pruebas:** Filtra las pruebas disponibles según la edad del usuario.  
✔️ **Cálculo de Notas:** Evalúa el rendimiento del usuario según su resultado en cada prueba.  
✔️ **Modo Claro/Oscuro:** Ajusta la interfaz según las preferencias del usuario.  

---

##  **Estructura del Proyecto**  
El código está organizado en diferentes paquetes para mantener una estructura clara y modular.  

📂 **ui/screens/** → Pantallas principales de la aplicación  
- `LoginScreen.kt` → Pantalla de inicio de sesión  
- `UserScreen.kt` → Captura la edad y género del usuario  
- `PruebasScreen.kt` → Lista las pruebas físicas disponibles  
- `NotasPruebas.kt` → Permite calcular la nota de cada prueba  

📂 **ui/components/** → Componentes reutilizables  
- `PruebaItem.kt` → Tarjeta que muestra la información de cada prueba  

📂 **Navigation/** → Gestión de la navegación  
- `NavGraph.kt` → Configuración de rutas entre pantallas  
- `Screens.kt` → Definición de las rutas de la app  

📂 **data/model/** → Modelos de datos  
- `Prueba.kt` → Representa una prueba física con su imagen y categoría  
- `Notas.kt` → Contiene la lógica para calcular las notas  

📂 **data/repository/** → Lógica de datos  
- `PruebasRepository.kt` → Define qué pruebas están disponibles según la edad
- `UserPreferences.kt` → Define para alamcenar las contraseñas de los usuarios


📂 **res/drawable/** → Recursos gráficos  
- `abdominales.png`, `flexibilidad.png`, `cooper.png`, etc.  

---

## 🔧 **Instrucciones de Instalación y Uso**  

###  **1. Instalación**  
Para ejecutar la aplicación en un entorno de desarrollo, sigue estos pasos:  

1️⃣ **Clonar el repositorio:**  
```bash
git clone https://github.com/usuario/MiPruebaFit.git
```
2️⃣ **Abrir en Android Studio.**  
3️⃣ **Compilar y ejecutar en un emulador o dispositivo físico.**  

###  **2. Uso de la Aplicación**  
✔️ **Paso 1:** Iniciar sesión en la aplicación.  
✔️ **Paso 2:** Ingresar la edad y el género.  
✔️ **Paso 3:** Seleccionar una prueba física.  
✔️ **Paso 4:** Introducir el resultado obtenido y calcular la nota.  

---

##  **Comentarios en el Código**  
El código incluye **comentarios claros y organizados** en cada archivo para facilitar su mantenimiento. Ejemplo:  

```kotlin
// Calcula la nota basada en la prueba seleccionada
fun calcularNota(nombrePrueba: String, resultado: Float, edad: Int, genero: String): String {
    return when (nombrePrueba) {
        "Abdominales" -> calcularNotaAbdominales(resultado, edad, genero)
        "Flexibilidad" -> calcularNotaFlexibilidad(resultado, edad, genero)
        else -> "0"
    }
}
```
