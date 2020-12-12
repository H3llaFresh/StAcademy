package by.vlfl.task7_mvvm

import android.app.Application
import by.vlfl.task7_mvvm.di.ApplicationComponent
import by.vlfl.task7_mvvm.di.DaggerApplicationComponent

class MainApp: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}