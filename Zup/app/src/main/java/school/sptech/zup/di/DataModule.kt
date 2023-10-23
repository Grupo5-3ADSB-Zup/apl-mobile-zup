package school.sptech.zup.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.network.ServiceProvider

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesServiceApi(
        serviceProvides: ServiceProvider
    ): ServiceApi{
        return serviceProvides.createService(ServiceApi::class.java)
    }
}