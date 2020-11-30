package by.vlfl.task4.presenter

import by.vlfl.task4.MainApp
import by.vlfl.task4.common.BasePresenter
import by.vlfl.task4.model.asRateEntity
import by.vlfl.task4.model.db.RateEntity
import by.vlfl.task4.view.MainView
import kotlinx.coroutines.*
import java.io.IOException

class MainPresenter(
    private val mainView: MainView
): BasePresenter() {

    fun handleAboutButtonClick() {
        mainView.navigateToAboutFragment()
    }

    fun loadRates() {
        var rates: List<RateEntity>
        val ratesDao = MainApp.instance.rateDatabase.rateDao()

            mainView.showLoading()
            runBlocking {
                try {
                    rates = MainApp.instance.ratesApiService.getRates().asRateEntity()
                    ratesDao.insertRates(rates)
                } catch (error: IOException) {
                    rates = ratesDao.getDatabaseRates()
                }
            }
        mainView.hideLoading()
        mainView.showRates(rates)
    }
}