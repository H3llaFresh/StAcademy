package by.vlfl.task4_moxy.presenter

import by.vlfl.task4_moxy.MainApp
import by.vlfl.task4_moxy.common.BasePresenter
import by.vlfl.task4_moxy.model.asRateEntity
import by.vlfl.task4_moxy.model.db.RateEntity
import by.vlfl.task4_moxy.view.MainView
import kotlinx.coroutines.*
import moxy.InjectViewState
import java.io.IOException

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    fun loadRates() {
        var rates: List<RateEntity>
        val ratesDao = MainApp.instance.rateDatabase.rateDao()

            viewState.showLoading()
            runBlocking {
                withContext(Dispatchers.IO) {
                    try {
                        rates = MainApp.instance.ratesApiService.getRates().asRateEntity()
                        ratesDao.insertRates(rates)
                    } catch (error: IOException) {
                        rates = ratesDao.getDatabaseRates()
                    }
                }
            }
        viewState.hideLoading()
        viewState.showRates(rates)
    }
}