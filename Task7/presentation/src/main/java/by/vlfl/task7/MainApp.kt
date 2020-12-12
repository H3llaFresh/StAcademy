package by.vlfl.task7

import android.app.Application
import by.vlfl.task7.di.ApplicationComponent
import by.vlfl.task7.di.DaggerApplicationComponent


class MainApp: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}