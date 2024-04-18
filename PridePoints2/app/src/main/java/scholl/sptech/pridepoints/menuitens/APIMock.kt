package scholl.sptech.pridepoints.menuitens

import retrofit2.Call
import retrofit2.http.GET
import scholl.sptech.pridepoints.menuitens.Avaliacoes

interface APIMock {
    @GET("/mockaval")
    fun get(): Call<List<Avaliacoes>>
}