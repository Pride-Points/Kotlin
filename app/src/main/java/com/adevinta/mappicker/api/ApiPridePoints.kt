package com.adevinta.mappicker.api

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
import com.adevinta.mappicker.classes.entidades.Avaliacao
import com.adevinta.mappicker.classes.entidades.AvaliacaoDTO
import com.adevinta.mappicker.classes.entidades.Credenciais
import com.adevinta.mappicker.classes.entidades.EmpresaFullDTO
import com.adevinta.mappicker.classes.entidades.EventosRegiao
import com.adevinta.mappicker.classes.entidades.ImagemPerfil
import com.adevinta.mappicker.classes.entidades.UsuarioCadastro
import com.adevinta.mappicker.classes.entidades.UsuarioToken

interface Apipridepoints {
    @GET("/empresas/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Response<EmpresaFullDTO>

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

    @PATCH("/users/imagem-perfil/{idUser}")
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