package by.vlfl.task6.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P: BasePresenter>: AppCompatActivity() {
    protected abstract fun getPresenter(): P?
}