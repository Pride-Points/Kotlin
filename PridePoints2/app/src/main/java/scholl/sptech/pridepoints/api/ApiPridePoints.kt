package scholl.sptech.pridepoints.api

import retrofit2.Response
import retrofit2.http.GET
import scholl.sptech.pridepoints.eventosregiao.EventosRegiao

interface ApiPridePoints {


    @GET("/eventos")
    suspend fun get(): Response<List<EventosRegiao>>
}