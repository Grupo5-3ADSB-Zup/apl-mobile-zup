package school.sptech.zup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import school.sptech.zup.data.repository.FeedRepositoryImpl
import school.sptech.zup.domain.repository.FeedRepository

@Module
@InstallIn(ViewModelComponent::class )
abstract class DomainModule {

    @Binds
    abstract fun bindsFeedRepositoryImpl(
        feedRepositoryImpl: FeedRepositoryImpl
    ): FeedRepository
}