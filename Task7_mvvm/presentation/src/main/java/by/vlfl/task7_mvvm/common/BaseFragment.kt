package by.vlfl.task7_mvvm.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel> : Fragment() {
    protected abstract fun getViewModel(): VM?

}