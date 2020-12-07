package by.vlfl.task5_arch.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vlfl.task5_arch.MainApp
import by.vlfl.task5_arch.model.Rate
import by.vlfl.task5_arch.model.asRateEntity
import by.vlfl.task5_arch.model.db.RateEntity
import kotlinx.coroutines.*
import java.io.IOException

class MainViewModel: ViewModel() {
    val isLoading = ObservableBoolean(false)
    val error = ObservableField<Throwable>()

    private var _rates = MutableLiveData<List<RateEntity>>()
    val rates: LiveData<List<RateEntity>> get() = _rates

    fun loadRates() {
        val ratesDao = MainApp.instance.rateDatabase.rateDao()
        var ratesList: List<RateEntity>
        isLoading.set(true)
        viewModelScope.launch {
            try {
                _rates.value = MainApp.instance.ratesApiService.getRates().asRateEntity()
                ratesDao.insertRates(rates.value!!)
            } catch (errorIO: IOException) {
                withContext(Dispatchers.IO) {
                    ratesList = ratesDao.getDatabaseRates()
                }
                withContext(Dispatchers.Main) {
                    _rates.value = ratesList
                    isLoading.set(false)
                    error.set(errorIO)
                }
            }
        }
        isLoading.set(false)

    }
}