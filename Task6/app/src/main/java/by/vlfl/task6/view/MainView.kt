package by.vlfl.task6.view

import by.vlfl.task6.common.BaseView
import by.vlfl.task6.model.db.RateEntity

interface MainView: BaseView {
    fun showRates(items: List<RateEntity>)
    fun navigateToAboutFragment()
}