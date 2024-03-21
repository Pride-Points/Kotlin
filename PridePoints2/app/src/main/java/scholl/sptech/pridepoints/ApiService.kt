package scholl.sptech.pridepoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/users/login")
    fun login(@Body credentials: Credentials): Call<UserResponse>

    @POST("/users")
    fun cadastrarUsuario(@Body usuario: FisicaCriacaoDTO): Call<UserResponse>
}

data class Credentials(val email: String, val senha: String)
data class FisicaCriacaoDTO(
    var nome: String,
    var email: String,
    var genero: String,
    var orientacaoSexual: String,
    var cpf: String,
    var senha: String
)

data class UserResponse(
    val token: String? = null,  // Usado na resposta de login
    val id: Long? = null,       // Usado na resposta de cadastro
    val message: String? = null // Mensagem genérica, pode ser usada em ambos os casos
)

