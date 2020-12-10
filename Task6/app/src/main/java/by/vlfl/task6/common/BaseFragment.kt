package by.vlfl.task6.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<P: BasePresenter> : Fragment() {
    protected abstract fun getPresenter(): P?
}