package scholl.sptech.pridepoints.menuitens

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitMock {
    const val BASE_URL = "https://65120ad0b8c6ce52b3954662.mockapi.io/mockaval/"

    fun getApiAvaliacoes(): APIMock {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIMock::class.java)
    }
}