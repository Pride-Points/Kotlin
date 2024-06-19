package com.adevinta.mappicker.api

import com.adevinta.mappicker.avaliacoes.AvaliacaoViewModel
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
import com.adevinta.mappicker.classes.entidades.AvaliacaoCriacaoDTO
import com.adevinta.mappicker.classes.entidades.AvaliacaoDTO
import com.adevinta.mappicker.classes.entidades.Credenciais
import com.adevinta.mappicker.classes.entidades.EmpresaFullDTO
import com.adevinta.mappicker.classes.entidades.EventosRegiao
import com.adevinta.mappicker.classes.entidades.ImagemPerfil
import com.adevinta.mappicker.classes.entidades.UsuarioCadastro
import com.adevinta.mappicker.classes.entidades.UsuarioToken

interface Apipridepoints {
    @GET("empresas/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Response<EmpresaFullDTO>

    @GET("eventos")
    suspend fun get(): Response<List<EventosRegiao>>

    @GET("avaliacoes")
    suspend fun getAvaliacoes(): Response<List<Avaliacao>>

    @GET("avaliacoes/{idEmpresa}")
    suspend fun getAvaliacoesByEmpresaId(@Path("idEmpresa") idEmpresa: Int): Response<List<AvaliacaoDTO>>
    @GET("avaliacoes/{idEmpresa}")
    suspend fun getAvaliacoesByEmpresaId2(
        @Path("idEmpresa") idEmpresa: Int,
        @Header("Authorization") token: String
    ): Response<List<AvaliacaoDTO>>


    @PUT("avaliacoes/{idAvaliacao}/{idUsuario}/{idEmpresa}")
    suspend fun updateAvaliacao(
        @Path("idAvaliacao") idAvaliacao: Int,
        @Path("idUsuario") idUsuario: Int,
        @Path("idEmpresa") idEmpresa: Int,
        @Header("Authorization") token: String,
        @Body novaAvaliacao: AvaliacaoDTO
    ): Response<AvaliacaoDTO>



    @DELETE("avaliacoes/{idAvaliacao}")
    suspend fun deleteAvaliacao(
        @Path("idAvaliacao") idAvaliacao: Int,
        @Header("Authorization") token: String
    ): Response<Unit>

    @GET("users/imagem-perfil/{idUser}")
    suspend fun getUserimage(@Path("iduser") idUser: Long): Response<ImagemPerfil>


    @POST("users/imagem-perfil/{idUser}")
    suspend fun patchUserImage(
        @Path("idUser") idUser: Long,
        @Body imagemPerfil: ImagemPerfil,
    ): Response<ImagemPerfil>

    @GET("avaliacoes/usuario/{idUsuario}")
    suspend fun getAvaliacoesUsuario(
        @Path("idUsuario") idUsuario: Long,
        @Header("Authorization") token: String
    ): Response<List<AvaliacaoDTO>>

    @GET("avaliacoes/usuario/{idUsuario}")
    suspend fun listarAvaliacoesDoUsuario(
        @Path("idUsuario") idUsuario: Long,
        @Header("Authorization") token: String
    ): Response<List<AvaliacaoDTO>>

    @POST("avaliacoes/{empresaId}/{usuarioId}")
    suspend fun postAvaliacao(
        @Body avaliacao: AvaliacaoCriacaoDTO,
        @Path("empresaId") empresaId: Long,
        @Path("usuarioId") usuarioId: Long,
        @Header("Authorization") token: String
    ): Response<AvaliacaoDTO>


    @POST("users/login")
    suspend fun login(@Body credenciais: Credenciais): Response<UsuarioToken>


    @POST("users")
    suspend fun cadastrarUsuario(@Body usuarioCadastro: UsuarioCadastro): Response<UsuarioToken>

    @GET("users/imagem-perfil/{idUser}")
    suspend fun getUserImage(
        @Path("idUser") idUser: Long,
        @Header("Authorization") token: String
    ): Response<ImagemPerfil>


}