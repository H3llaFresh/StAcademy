package by.vlfl.task7_mvvm.di.module

import by.vlfl.data.RatesRepository
import by.vlfl.data.db.RateRoomDatabase
import by.vlfl.data.network.api.ExRatesApiService
import by.vlfl.domain.repository.IRatesRepository
import by.vlfl.domain.usecase.GetSortedRatesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RatesModule {

    @Provides
    @Singleton
    fun provideRatesRepository(service: ExRatesApiService, database: RateRoomDatabase): IRatesRepository {
        return RatesRepository(
            ratesApiService = service,
            rateDatabase = database
        )
    }

    @Provides
    fun provideGetSortedRatesUseCase(repository: IRatesRepository): GetSortedRatesUseCase {
        return GetSortedRatesUseCase(
            repository = repository
        )
    }

}