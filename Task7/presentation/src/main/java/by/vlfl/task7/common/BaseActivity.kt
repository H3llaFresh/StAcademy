package by.vlfl.task7.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P: BasePresenter>: AppCompatActivity() {
    protected abstract fun getPresenter(): P?
}