package school.sptech.zup.data.repository

import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : FeedRepository {
    override suspend fun getFeed(): FeedResponse {
      return serviceApi.getPost()
    }
}