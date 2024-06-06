package scholl.sptech.pridepoints.conexao

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import scholl.sptech.pridepoints.classes.Credenciais
import scholl.sptech.pridepoints.classes.UsuarioCadastro
import scholl.sptech.pridepoints.classes.UsuarioToken

interface ApiService {
    @POST("/users/login")
    fun login(@Body credenciais: Credenciais): Call<UsuarioToken>

    @POST("/users")
    fun cadastrarUsuario(@Body usuario: UsuarioCadastro): Call<UsuarioToken>
}





