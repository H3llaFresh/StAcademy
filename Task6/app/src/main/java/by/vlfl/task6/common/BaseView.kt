package by.vlfl.task6.common

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(throwable: Throwable)
}
