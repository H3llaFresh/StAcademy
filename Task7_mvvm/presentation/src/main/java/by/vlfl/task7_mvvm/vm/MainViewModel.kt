package by.vlfl.task7_mvvm.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.vlfl.domain.model.Rate
import by.vlfl.domain.usecase.GetSortedRatesUseCase
import by.vlfl.task7_mvvm.common.BaseViewModel
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSortedRatesUseCase: GetSortedRatesUseCase
) : BaseViewModel() {
    val isLoading = ObservableBoolean(false)
    val rates = ObservableField<List<Rate>>(emptyList())
    val error = ObservableField<Throwable>()

    fun loadRates() {
        var ratesList: List<Rate> = listOf()

        isLoading.set(true)
        runBlocking {
            try {
                ratesList = getSortedRatesUseCase.execute()
            } catch (errorIO: IOException) {
                isLoading.set(false)
                error.set(errorIO)
            }
        }
        isLoading.set(false)
        rates.set(ratesList)
    }
}