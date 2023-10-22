package school.sptech.zup.domain.repository

import school.sptech.zup.data.model.FeedResponse

interface FeedRepository {
    suspend fun getFeed(): FeedResponse
}