package by.vlfl.task6.presenter

import by.vlfl.task6.common.BasePresenter
import by.vlfl.task6.model.ExRatesApiService
import by.vlfl.task6.model.asRateEntity
import by.vlfl.task6.model.db.RateEntity
import by.vlfl.task6.model.db.RateRoomDatabase
import by.vlfl.task6.view.FragmentMain
import by.vlfl.task6.view.MainView
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val ratesApiService: ExRatesApiService, private val rateDatabase: RateRoomDatabase
): BasePresenter() {

    private lateinit var mainView: MainView


    fun handleAboutButtonClick() {
        mainView.navigateToAboutFragment()
    }

    fun setView(view: FragmentMain) {
        mainView = view
    }

    fun loadRates() {
        var rates: List<RateEntity>
        val ratesDao = rateDatabase.rateDao()

            mainView.showLoading()
            runBlocking {
                try {
                    rates = ratesApiService.getRates().asRateEntity()
                    ratesDao.insertRates(rates)
                } catch (error: IOException) {
                    rates = ratesDao.getDatabaseRates()
                }
            }
        mainView.hideLoading()
        mainView.showRates(rates)
    }
}