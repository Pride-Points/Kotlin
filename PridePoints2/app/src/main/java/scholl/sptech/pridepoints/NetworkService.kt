package scholl.sptech.pridepoints

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class NetworkService {
    fun performLogin(email: String, senha: String): Call<UserResponse> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        return service.login(Credentials(email, senha))
    }
}
