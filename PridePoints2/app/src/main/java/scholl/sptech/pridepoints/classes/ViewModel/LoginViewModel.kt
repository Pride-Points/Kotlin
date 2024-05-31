package scholl.sptech.pridepoints.classes.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.api.RetrofitService
import scholl.sptech.pridepoints.classes.entidades.Credenciais
import scholl.sptech.pridepoints.storage.DataStoreManager

class LoginViewModel() : ViewModel() {
    private val _loginResult = MutableLiveData<Pair<Boolean, String>>()
    val loginResult: LiveData<Pair<Boolean, String>> = _loginResult
    val erroApi = MutableLiveData("")

    fun realizarLogin(context: Context, email: String, senha: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApiPridePointsService()
                val credenciais = Credenciais(email, senha)
                val response = apiService.login(credenciais)
                if (response.isSuccessful) {
                    val usuarioToken = response.body()
                    if (usuarioToken != null) {
                        val dataStoreManager = DataStoreManager(context)
                        dataStoreManager.saveUserToken(usuarioToken.userId, usuarioToken.token)
                        _loginResult.value = Pair(true, "Login bem-sucedido!")
                    } else {
                        _loginResult.value = Pair(false, "Login ou Senha incorretos!")
                    }
                } else {
                    _loginResult.value = Pair(false, "Login ou Senha incorretos!")
                }
            } catch (e: Exception) {
                _loginResult.value = Pair(false, "Erro: ${e.message}")
            }
        }
    }

    fun getImgUser(context: Context){
        val dataStoreManager = DataStoreManager(context)
        viewModelScope.launch {
            val token = dataStoreManager.token.first()
            val userId = dataStoreManager.userId.first()
            try {
                val apiService = RetrofitService.getApiPridePointsService()

                if (userId != null && token != null) {
                    val response = apiService.getUserImage(userId, "Bearer $token")
                    if(response.isSuccessful){
                        val imgPerfilObj = response.body()
                        if(imgPerfilObj !=null){
                            imgPerfilObj.imgUser?.let { dataStoreManager.saveUserImageUrl(it) }
                        }
                    }else {
                        Log.e("api", "erro no get da imagem")
                        erroApi.postValue(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            } catch (e: Exception) {
                Log.e("api", "Exception in get image! ${e.message}")
                erroApi.postValue(e.message ?: "Unknown error")
            }
        }
    }
}
