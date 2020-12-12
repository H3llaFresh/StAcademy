package by.vlfl.task7.presenter

import by.vlfl.domain.model.Rate
import by.vlfl.domain.usecase.GetSortedRatesUseCase
import by.vlfl.task7.common.BasePresenter
import by.vlfl.task7.view.FragmentMain
import by.vlfl.task7.view.MainView
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val getSortedRatesUseCase: GetSortedRatesUseCase
): BasePresenter() {

    private lateinit var mainView: MainView

    fun handleAboutButtonClick() {
        mainView.navigateToAboutFragment()
    }

    fun setView(view: FragmentMain) {
        mainView = view
    }

    fun loadRates() {
        var rates: List<Rate> = listOf()

            mainView.showLoading()
            runBlocking {
                try {
                    rates = getSortedRatesUseCase.execute()
                } catch (error: IOException) {
                    mainView.showError(error)
                }
            }
        mainView.hideLoading()
        mainView.showRates(rates)
    }
}