package app.mobilemobile.owwow.api

import app.mobilemobile.owwow.data.Wow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://owen-wilson-wow-api.herokuapp.com"
private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface OWWowService {
    @GET("/wows/random")
    suspend fun getRandomWows(@Query("results") results: Int? = null): List<Wow>
}

object OWWowApi {
    val retrofitService: OWWowService by lazy {
        retrofit.create(OWWowService::class.java)
    }
}
