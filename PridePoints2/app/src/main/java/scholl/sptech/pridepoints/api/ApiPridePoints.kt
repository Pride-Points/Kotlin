package scholl.sptech.pridepoints.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import scholl.sptech.pridepoints.avaliacoes.Avaliacao
import scholl.sptech.pridepoints.eventosregiao.EventosRegiao

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
}