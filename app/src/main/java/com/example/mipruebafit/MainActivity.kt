package com.example.mipruebafit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mipruebafit.Navigation.NavigationWrapper
import com.example.mipruebafit.ui.theme.MiPruebaFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Controlar si el modo oscuro esta activado o no
            var isDarkModeEnabled by rememberSaveable { mutableStateOf(false) }

            // Para el switch (si esta activado o no)
            var switchChecked by rememberSaveable { mutableStateOf(false) }

            // Cogemos iconos para en el switch presentar los modos (claro y oscuro)
            val lightModeIcon = painterResource(R.drawable.icons8_no_molestar_2_50)
            val darkModeIcon = painterResource(R.drawable.icons8_modo_oscuro_50)

            // Aplicar el tema dependiendo del estado de isDarkModeEnabled
            MiPruebaFitTheme(darkTheme = isDarkModeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {

                        // Cargamos la navegación principal
                        NavigationWrapper(modifier = Modifier)

                        // Switch para cambiar entre modo claro y oscuro
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.TopEnd), // Alineado el switch en la parte superior derecha en todas las pantallas
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Texto que indica el modo actual
                            Text(text = if (switchChecked) "Modo Oscuro" else "Modo Claro")

                            Spacer(modifier = Modifier.width(8.dp))

                            // Switch para cambiar el tema
                            Switch(
                                checked = switchChecked,
                                onCheckedChange = {
                                    switchChecked = it
                                    isDarkModeEnabled =
                                        !isDarkModeEnabled // Cambiamos el tema con el switch
                                },
                                thumbContent = if (switchChecked) {
                                    {
                                        Icon(
                                            painter = darkModeIcon,
                                            contentDescription = "Modo oscuro"
                                        )
                                    }
                                } else {
                                    {
                                        Icon(
                                            painter = lightModeIcon,
                                            contentDescription = "Modo claro"
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}