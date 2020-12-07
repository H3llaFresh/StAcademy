package by.vlfl.task5.common

import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel> : Fragment() {
    protected abstract fun getViewModel(): VM?

}