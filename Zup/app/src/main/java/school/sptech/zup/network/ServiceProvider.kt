package school.sptech.zup.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.presenter.feed.Feed
import java.util.concurrent.TimeUnit


object ServiceProvider {
    const val BASE_URL = "http://54.226.87.173:8080/"

    val client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(client).build()

    val service = retrofit.create(ServiceApi::class.java)
}



