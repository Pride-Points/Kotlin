package com.adevinta.leku.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val BASE_URL_FILMES = "https://pride-points.zapto.org/api/"

    fun getApipridepointsService(): Apipridepoints {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_FILMES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apipridepoints::class.java)

        return cliente
    }
}