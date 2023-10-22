package school.sptech.zup.domain.usecase

import school.sptech.zup.data.mapper.toDomain
import school.sptech.zup.domain.model.Feed
import school.sptech.zup.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: FeedRepository
){

    suspend operator fun invoke(): Feed{
        return repository.getFeed().toDomain()
    }

}