package scholl.sptech.pridepoints.menuitens

import androidx.compose.runtime.MutableState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import scholl.sptech.pridepoints.menuitens.avaliacoes.Avaliacoes

interface APIMock {
    @GET("/mockaval")
    fun get(): Call<List<Avaliacoes>>

    @PUT("/mockaval/{id}")
    fun put(@Path("id") id:Int, @Body avaliacoes: Avaliacoes): Call<Avaliacoes>

    @DELETE("/mockaval/{id}")
    fun delete(@Path("id") id:Int): Call<ResponseBody>
}