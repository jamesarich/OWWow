package app.mobilemobile.owwow.api

import app.mobilemobile.owwow.data.Wow
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://owen-wilson-wow-api.herokuapp.com"
private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
    .build()

interface OWWowService {
    @GET("/wows/random")
    suspend fun getRandomWows(@Query("results") results: Int? = null): ApiResponse<List<Wow>>
}

object OWWowApi {
    val retrofitService: OWWowService by lazy {
        retrofit.create(OWWowService::class.java)
    }
}
