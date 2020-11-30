package by.vlfl.task4.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<P: BasePresenter> : Fragment() {
    protected abstract fun getPresenter(): P?
}