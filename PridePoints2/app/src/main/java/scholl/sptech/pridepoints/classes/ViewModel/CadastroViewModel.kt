package scholl.sptech.pridepoints.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.api.RetrofitService
import scholl.sptech.pridepoints.classes.entidades.UsuarioCadastro
class CadastroViewModel : ViewModel() {
    private val _cadastroResult = MutableLiveData<Pair<Boolean, String>>()
    val cadastroResult: LiveData<Pair<Boolean, String>> = _cadastroResult

    fun cadastrarUsuario(usuarioCadastro: UsuarioCadastro) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApiPridePointsService()
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
