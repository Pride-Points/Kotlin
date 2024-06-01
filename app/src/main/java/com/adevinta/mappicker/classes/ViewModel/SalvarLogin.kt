package com.adevinta.mappicker.classes.ViewModel

import android.content.Context
import android.content.SharedPreferences
import com.adevinta.mappicker.classes.entidades.UsuarioToken

class SalvarLogin(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "pridepointsPrefs"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "userId"
        private const val KEY_NOME = "nome"
    }

    fun saveUserToken(usuarioToken: UsuarioToken) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, usuarioToken.token)
        editor.putLong(KEY_USER_ID, usuarioToken.userId)
        editor.putString(KEY_NOME, usuarioToken.nome)
        editor.apply()
    }

    fun getUserToken(): UsuarioToken? {
        val token = sharedPreferences.getString(KEY_TOKEN, null)
        val userId = sharedPreferences.getLong(KEY_USER_ID, -1)
        val nome = sharedPreferences.getString(KEY_NOME, null)

        return if (token != null && userId != -1L && nome != null) {
            UsuarioToken(token, userId, nome)
        } else {
            null
        }
    }

    fun clearUserToken() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_USER_ID)
        editor.remove(KEY_NOME)
        editor.apply()
    }
}