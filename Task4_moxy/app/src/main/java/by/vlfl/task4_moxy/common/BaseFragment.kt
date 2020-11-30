package by.vlfl.task4_moxy.common

import moxy.MvpAppCompatFragment

abstract class BaseFragment<P: BasePresenter<*>> : MvpAppCompatFragment() {
    open lateinit var presenter: P
}