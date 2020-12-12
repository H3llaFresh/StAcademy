package by.vlfl.task7_mvvm_arch

import android.app.Application
import by.vlfl.task7_mvvm_arch.di.DaggerApplicationComponent

class MainApp : Application() {

    val applicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }
}