package by.vlfl.task4_moxy.view

import by.vlfl.task4_moxy.common.BaseView
import by.vlfl.task4_moxy.model.db.RateEntity
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: BaseView {
    fun showRates(items: List<RateEntity>)
    fun navigateToAboutFragment()
}