package by.vlfl.task6_mvvm.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel> : Fragment() {
    protected abstract fun getViewModel(): VM?

}