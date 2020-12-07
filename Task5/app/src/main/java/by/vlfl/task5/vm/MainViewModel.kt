package by.vlfl.task5.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.vlfl.task5.MainApp
import by.vlfl.task5.common.BaseViewModel
import by.vlfl.task5.model.asRateEntity
import by.vlfl.task5.model.db.RateEntity
import kotlinx.coroutines.*
import java.io.IOException

class MainViewModel: BaseViewModel() {
    val isLoading = ObservableBoolean(false)
    val rates = ObservableField<List<RateEntity>>(emptyList())
    val error = ObservableField<Throwable>()

    fun loadRates() {
        var ratesList: List<RateEntity>
        val ratesDao = MainApp.instance.rateDatabase.rateDao()

        isLoading.set(true)
        runBlocking {
            try {
                ratesList = MainApp.instance.ratesApiService.getRates().asRateEntity()
                ratesDao.insertRates(ratesList)
            } catch (errorIO: IOException) {
                withContext(Dispatchers.IO) {
                    ratesList = ratesDao.getDatabaseRates()
                }
                isLoading.set(false)
                error.set(errorIO)
            }
        }
        isLoading.set(false)
        rates.set(ratesList)
    }
}