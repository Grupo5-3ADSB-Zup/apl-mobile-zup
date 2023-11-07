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
        companion object {
             //const val BASE_URL = "https://44.219.155.152:8443/"
             const val BASE_URL = "http://67.202.17.158:8080/"
            //const val BASE_URL = "http://localhost:8080/"


            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            val gson: Gson
                get() = Gson()

            suspend fun getPost(): FeedResponse {
                val request = Request.Builder()
                    .url(BASE_URL + "noticias/feed")
                    .build()

                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                // Use o Gson para desserializar a resposta JSON em um objeto FeedResponse
                return gson.fromJson(responseBody, FeedResponse::class.java)
            }

            suspend fun saveUser(registerUser: RegisterRequest): RegisterResponse {
                val jsonMediaType = "application/json; charset=utf-8".toMediaType()
                val requestBody = gson.toJson(registerUser).toRequestBody(jsonMediaType)

                val request = Request.Builder()
                    .url(BASE_URL + "cadastro/user/comum")
                    .post(requestBody)
                    .build()

    @GET("/noticia/rss/forbes")
    fun getFeed(): Call<List<FeedRequest>>

    @POST("/cadastro/user/comum")
    fun saveUser(@Body registerUser: RegisterResponse): Call<RegisterResponse>

    @POST("/login/logar")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

                val request = Request.Builder()
                    .url(BASE_URL + "login/logar")
                    .post(requestBody)
                    .build()

                return request
            }

            fun cadastroPerfilUsuarioCoumu(dadosEnvioApiFormularioPerfil: DadosEnvioApiFormularioPerfil): Request {
                val jsonMediaType = "application/json; charset=utf-8".toMediaType()
                val requestBody = gson.toJson(dadosEnvioApiFormularioPerfil).toRequestBody(jsonMediaType)

                val request = Request.Builder()
                    .url(BASE_URL + "mobile/usuarios/perfil")
                    .post(requestBody)
                    .build()

                return request
            }
        }
}