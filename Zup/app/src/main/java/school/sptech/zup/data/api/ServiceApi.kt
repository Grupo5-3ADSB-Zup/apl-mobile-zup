package school.sptech.zup.data.api

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.data.model.request.LoginRequest
import school.sptech.zup.data.model.response.LoginResponse

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.logging.HttpLoggingInterceptor
import school.sptech.zup.domain.model.RegisterRequest
import java.io.IOException
import java.util.concurrent.TimeUnit

public interface ServiceApi {
        companion object {
             //const val BASE_URL = "https://44.219.155.152:8443/"
             const val BASE_URL = "http://54.172.32.189:8080/"
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

                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                // Use o Gson para desserializar a resposta JSON em um objeto RegisterResponse
                return gson.fromJson(responseBody, RegisterResponse::class.java)
            }

            fun loginUser(loginUser: LoginRequest): Request {
                val jsonMediaType = "application/json; charset=utf-8".toMediaType()
                val requestBody = gson.toJson(loginUser).toRequestBody(jsonMediaType)

                val request = Request.Builder()
                    .url(BASE_URL + "login/logar")
                    .post(requestBody)
                    .build()

                return request
            }
        }
}