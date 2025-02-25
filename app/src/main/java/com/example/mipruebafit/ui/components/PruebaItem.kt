package com.example.mipruebafit.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mipruebafit.Data.model.Prueba


//Mostramos una Card para que quede bonito con la informacion de la pruebas fisicas de PruebasRepository
//@prueba Objeto de contiene nel nombre, imagen, enlace
//@pruebaSelected funcion que se ejecuta cuando hacemos click en una tarjeta
@Composable
fun PruebaItem(prueba: Prueba, pruebaSelected: (String) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { pruebaSelected(prueba.nombre) }, // Cuando selecionamos la tarjeta, ejecutamis pruebaSelected
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = prueba.imagen),
                contentDescription = prueba.nombre,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) { /// weight(1f) para asi que la columna ocupe todo el espacio disponible de la pantalla
                Text(text = prueba.nombre, style = MaterialTheme.typography.bodyLarge)

                Text(
                    text = "Más información",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary, // Color del enlace
                    modifier = Modifier.clickable {
                        // Creamos un Intent para abrir la URL en el navegador
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(prueba.enlace))
                        context.startActivity(intent) // Abrimos el navgador con la URL de la prueba
                    }
                )
            }
        }
    }
}