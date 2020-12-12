package by.vlfl.task7_mvvm_arch.vm

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vlfl.domain.model.Rate
import by.vlfl.domain.usecase.GetSortedRatesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSortedRatesUseCase: GetSortedRatesUseCase
) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    val error = ObservableField<Throwable>()

    private var _rates = MutableLiveData<List<Rate>>()
    val rates: LiveData<List<Rate>> get() = _rates

    fun loadRates() {
        isLoading.set(true)
        viewModelScope.launch {
            try {
                _rates.value = getSortedRatesUseCase.execute()
            } catch (errorIO: IOException) {
                withContext(Dispatchers.Main) {
                    error.set(errorIO)
                }
            }
        }
        isLoading.set(false)

    }
}