package by.vlfl.task4_moxy.common

import moxy.MvpPresenter
import moxy.MvpView


abstract class BasePresenter<T: MvpView>: MvpPresenter<T>() {

}