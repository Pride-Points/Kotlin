package com.adevinta.mappicker.classes.DataStorage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
    companion object {
        val USER_ID = longPreferencesKey("user_id")
        val TOKEN = stringPreferencesKey("token")
        val USER_IMAGE_URL = stringPreferencesKey("user_image_url")
    }

    // Existente: Métodos para salvar dados
    suspend fun saveUserToken(userId: Long, token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[TOKEN] = token
        }
    }

    suspend fun saveUserImageUrl(imageUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_IMAGE_URL] = imageUrl
        }
    }


    // Existente: Flows para ler dados
    val token: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN]
    }
    val userId: Flow<Long?> = context.dataStore.data.map { preferences ->
        preferences[USER_ID]
    }
    val userImageUrl: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_IMAGE_URL]
    }
    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}