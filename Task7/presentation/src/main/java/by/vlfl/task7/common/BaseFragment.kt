package by.vlfl.task7.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<P: BasePresenter> : Fragment() {
    protected abstract fun getPresenter(): P?
}