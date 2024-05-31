package scholl.sptech.pridepoints.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import scholl.sptech.pridepoints.classes.entidades.Avaliacao
import scholl.sptech.pridepoints.classes.entidades.AvaliacaoDTO
import scholl.sptech.pridepoints.classes.entidades.Credenciais
import scholl.sptech.pridepoints.classes.entidades.EventosRegiao
import scholl.sptech.pridepoints.classes.entidades.ImagemPerfil
import scholl.sptech.pridepoints.classes.entidades.UsuarioCadastro
import scholl.sptech.pridepoints.classes.entidades.UsuarioToken

interface ApiPridePoints {


    @GET("/eventos")
    suspend fun get(): Response<List<EventosRegiao>>

    @GET("/avaliacoes")
    suspend fun getAvaliacoes(): Response<List<Avaliacao>>

    @GET("/avaliacoes/{idEmpresa}")
    suspend fun getAvaliacoesByEmpresaId(@Path("idEmpresa") idEmpresa: Int): Response<List<Avaliacao>>

    @PUT("/avaliacoes/{id}")
    suspend fun updateAvaliacao(@Path("id") id: Int, @Body novaAvaliacao: Avaliacao): Response<Avaliacao>
    @DELETE("/avaliacoes/{idAvaliacao}")
    suspend fun deleteAvaliacao(@Path("idAvaliacao") idAvaliacao: Int): Response<Unit>

    @GET("/avaliacoes/usuario/{idUsuario}")
    suspend fun getAvaliacoesUsuario(
        @Path("idUsuario") idUsuario: Long,
        @Header("Authorization") token: String
    ): Response<List<AvaliacaoDTO>>

    @GET("/users/imagem-perfil/{idUser}")
    suspend fun getUserimage(@Path("iduser") idUser: Long): Response<ImagemPerfil>

    @POST("/users/imagem-perfil/{idUser}")
    suspend fun patchUserImage(
        @Path("idUser") idUser: Long,
        @Body imagemPerfil: ImagemPerfil,
    ): Response<ImagemPerfil>

    @POST("/avaliacoes/{empresaId}/{usuarioId}")
    suspend fun postAvaliacao(
        @Body avaliacaoCriacaoDTO: Avaliacao,
        @Path("empresaId") empresaId: Long,
        @Path("usuarioId") usuarioId: Long
    ): Response<Avaliacao>

    @POST("/users/login")
    suspend fun login(@Body credenciais: Credenciais): Response<UsuarioToken>


    @POST("/users")
    suspend fun cadastrarUsuario(@Body usuarioCadastro: UsuarioCadastro): Response<UsuarioToken>

    @GET("/users/imagem-perfil/{idUser}")
    suspend fun getUserImage(
        @Path("idUser") idUser: Long,
        @Header("Authorization") token: String
    ): Response<ImagemPerfil>


}