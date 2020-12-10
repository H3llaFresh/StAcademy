package by.vlfl.task6

import android.app.Application
import by.vlfl.task6.di.ApplicationComponent
import by.vlfl.task6.di.DaggerApplicationComponent


class MainApp: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}