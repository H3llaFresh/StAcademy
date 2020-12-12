package by.vlfl.task7.common

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(throwable: Throwable)
}
