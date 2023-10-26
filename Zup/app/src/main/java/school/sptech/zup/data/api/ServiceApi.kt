package school.sptech.zup.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.domain.model.Register

interface ServiceApi {

    @GET("noticias/feed")
    suspend fun getPost(): Call<List<FeedResponse>>

    @POST("cadastro/user/comum")
    fun saveUser(@Body registerUser: Register ): Call<RegisterResponse>
}