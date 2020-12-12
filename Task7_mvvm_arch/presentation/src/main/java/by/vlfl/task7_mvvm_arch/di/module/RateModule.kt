package by.vlfl.task7_mvvm_arch.di.module

import by.vlfl.data.RatesRepository
import by.vlfl.data.db.RateRoomDatabase
import by.vlfl.data.network.api.ExRatesApiService
import by.vlfl.domain.usecase.GetSortedRatesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RateModule {
    @Singleton
    @Provides
    fun provideRepository(service: ExRatesApiService, database: RateRoomDatabase):RatesRepository {
        return RatesRepository(
            ratesApiService = service,
            rateDatabase = database
        )
    }

    @Singleton
    @Provides
    fun provideGetSortedRatesUseCase(repository: RatesRepository): GetSortedRatesUseCase {
        return GetSortedRatesUseCase(
            repository = repository
        )
    }
}