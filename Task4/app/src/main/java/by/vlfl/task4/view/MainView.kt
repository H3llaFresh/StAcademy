package by.vlfl.task4.view

import by.vlfl.task4.common.BaseView
import by.vlfl.task4.model.db.RateEntity

interface MainView: BaseView {
    fun showRates(items: List<RateEntity>)
    fun navigateToAboutFragment()
}