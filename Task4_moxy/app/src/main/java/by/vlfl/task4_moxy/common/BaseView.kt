package by.vlfl.task4_moxy.common

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface BaseView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showError(throwable: Throwable)
}