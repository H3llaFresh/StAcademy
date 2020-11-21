package by.vlfl.task4.common

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(throwable: Throwable)
}