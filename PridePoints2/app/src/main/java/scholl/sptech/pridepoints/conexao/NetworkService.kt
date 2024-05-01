package scholl.sptech.pridepoints.conexao

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import scholl.sptech.pridepoints.classes.Credenciais
import scholl.sptech.pridepoints.classes.UsuarioToken

class NetworkService {

    val BASE_URL = "http://10.0.2.2:8080/"
    fun realizarLogin(email: String, senha: String): Call<UsuarioToken> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        return service.login(Credenciais(email, senha))
    }
}
