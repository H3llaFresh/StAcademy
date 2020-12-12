package by.vlfl.data

import by.vlfl.data.db.RateEntity
import by.vlfl.data.db.RateRoomDatabase
import by.vlfl.data.network.api.ExRatesApiService
import by.vlfl.data.network.api.asRateEntity
import by.vlfl.domain.model.Rate
import by.vlfl.domain.repository.IRatesRepository
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class RatesRepository @Inject constructor(private val ratesApiService: ExRatesApiService, private val rateDatabase: RateRoomDatabase): IRatesRepository {
    override fun getRates(): List<Rate> {
        var rates: List<RateEntity>
        val ratesDao = rateDatabase.rateDao()

        runBlocking {
            try {
                rates = ratesApiService.getRates().asRateEntity()
                ratesDao.insertRates(rates)
            } catch (error: IOException) {
                rates = ratesDao.getDatabaseRates()
            }
        }
        return rates.map {
            Rate (
                it.curID,
                it.curCode,
                it.curName,
                it.curNameBel,
                it.curScale
            )
        }
    }
}