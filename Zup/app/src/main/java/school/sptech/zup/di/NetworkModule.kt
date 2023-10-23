package school.sptech.zup.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import school.sptech.zup.network.ServiceProvider

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun providesServiceProvider() = ServiceProvider
}