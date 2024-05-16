package scholl.sptech.pridepoints.classes.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import scholl.sptech.pridepoints.api.RetrofitService
import scholl.sptech.pridepoints.classes.entidades.Credenciais
import scholl.sptech.pridepoints.classes.entidades.UsuarioToken

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Pair<Boolean, String>>()
    val loginResult: LiveData<Pair<Boolean, String>> = _loginResult

    fun realizarLogin(context: Context,email: String, senha: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApiPridePointsService()
                val credenciais = Credenciais(email, senha)
                val response = apiService.login(credenciais)
                if (response.isSuccessful) {
                    val usuarioToken = response.body()
                    if (usuarioToken != null) {
                        val salvarLogin = SalvarLogin(context)
                        salvarLogin.saveUserToken(usuarioToken)

                        // Salve o token do usuário conforme necessário
                        _loginResult.value = Pair(true, "Login bem-sucedido!")
                    } else {
                        Toast.makeText(context, "Login ou Senha incorretos!", Toast.LENGTH_LONG).show()

                        _loginResult.value = Pair(false, "Login ou Senha incorretos!")
                    }
                } else {
                    Toast.makeText(context, "Login ou Senha incorretos!", Toast.LENGTH_LONG).show()

                    _loginResult.value = Pair(false, "Login ou Senha incorretos!")
                }
            } catch (e: HttpException) {
                Toast.makeText(context, "Login ou Senha incorretos!", Toast.LENGTH_LONG).show()

                _loginResult.value = Pair(false, "Erro: ${e.message}")
            } catch (e: Exception) {
                Toast.makeText(context, "Login ou Senha incorretos!", Toast.LENGTH_LONG).show()

                _loginResult.value = Pair(false, "Erro: ${e.message}")
            }
        }
    }
}
