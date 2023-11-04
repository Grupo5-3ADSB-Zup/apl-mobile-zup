package school.sptech.zup.data.api

import school.sptech.zup.domain.model.LoginRequest
import school.sptech.zup.data.model.LoginResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import school.sptech.zup.domain.model.FeedRequest
import school.sptech.zup.data.model.RegisterResponse

public interface ServiceApi {

    @GET("/noticia/rss/forbes")
    fun getFeed(): Call<List<FeedRequest>>

    @POST("/cadastro/user/comum")
    fun saveUser(@Body registerUser: RegisterResponse): Call<RegisterResponse>

    @POST("/login/logar")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

}