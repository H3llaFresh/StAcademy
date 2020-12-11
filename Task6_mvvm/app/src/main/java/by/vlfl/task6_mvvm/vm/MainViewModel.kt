package by.vlfl.task6_mvvm.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.vlfl.task6_mvvm.MainApp
import by.vlfl.task6_mvvm.common.BaseViewModel
import by.vlfl.task6_mvvm.model.ExRatesApiService
import by.vlfl.task6_mvvm.model.asRateEntity
import by.vlfl.task6_mvvm.model.db.RateEntity
import by.vlfl.task6_mvvm.model.db.RateRoomDatabase
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val ratesApiService: ExRatesApiService,
    private val rateDatabase: RateRoomDatabase
) : BaseViewModel() {
    val isLoading = ObservableBoolean(false)
    val rates = ObservableField<List<RateEntity>>(emptyList())
    val error = ObservableField<Throwable>()

    fun loadRates() {
        var ratesList: List<RateEntity>
        val ratesDao = rateDatabase.rateDao()

        isLoading.set(true)
        runBlocking {
            try {
                ratesList = ratesApiService.getRates().asRateEntity()
                ratesDao.insertRates(ratesList)
            } catch (errorIO: IOException) {
                ratesList = ratesDao.getDatabaseRates()
                isLoading.set(false)
                error.set(errorIO)
            }
        }
        isLoading.set(false)
        rates.set(ratesList)
    }
}