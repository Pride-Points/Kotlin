package com.adevinta.mappicker.classes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.entidades.UsuarioCadastro
import kotlinx.coroutines.launch
class CadastroViewModel : ViewModel() {
    private val _cadastroResult = MutableLiveData<Pair<Boolean, String>>()
    val cadastroResult: LiveData<Pair<Boolean, String>> = _cadastroResult

    fun cadastrarUsuario(usuarioCadastro: UsuarioCadastro) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApipridepointsService()
                val response = apiService.cadastrarUsuario(usuarioCadastro)
                if (response.isSuccessful) {
                    _cadastroResult.value = Pair(true, "Cadastro realizado com sucesso")
                } else {
                    _cadastroResult.value = Pair(false, "Falha no cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                _cadastroResult.value = Pair(false, "Erro: ${e.message}")
            }
        }
    }
}
