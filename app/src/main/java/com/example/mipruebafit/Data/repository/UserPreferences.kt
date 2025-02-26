package com.example.mipruebafit.Data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Creanmos un almacen de datos (DataStore) llamado "user_prefs"
// que nos servira para guardar información del usuario, como la contraseña
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    // Utilizamo esta llave que vamos a usar para guardar y recuperar la contraseña en DataStore
    private val PASSWORD_KEY = stringPreferencesKey("user_password")

    /**
     *  Creanmos una variable password que nos permite obtener la contraseña guardada
     *
     *  Esto devuelve un Flow<String>, lo que significa que se actualizara en tiempo real si cambia.
     *  Si no hay ninguna contraseña guardada, por defecto usamos root
     */

    val password: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PASSWORD_KEY] ?: "root" // Si no hay contraseña guardada ponemos la de root
        }

    /**
     *  Actualizar la contraseña.
     *
     *  Recibe la nueva contraseña que queremos guardar
     *  Luego accedemos a DataStore y la actualiza
     *  Como es una funcion suspend, hay que llamarla dentro de una corrutina
     */
    suspend fun updatePassword(newPassword: String) {
        context.dataStore.edit { preferences ->
            preferences[PASSWORD_KEY] = newPassword // Guardamos la nueva contraseña
        }
    }
}
