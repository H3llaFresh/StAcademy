package by.vlfl.task7.view

import by.vlfl.domain.model.Rate
import by.vlfl.task7.common.BaseView

interface MainView: BaseView {
    fun showRates(items: List<Rate>)
    fun navigateToAboutFragment()
}