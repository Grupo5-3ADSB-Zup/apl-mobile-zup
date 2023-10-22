package school.sptech.zup.data.api

import retrofit2.http.GET
import school.sptech.zup.data.model.FeedResponse

interface ServiceApi {

    @GET("/noticias/feed")
    suspend fun getPost(): FeedResponse
}