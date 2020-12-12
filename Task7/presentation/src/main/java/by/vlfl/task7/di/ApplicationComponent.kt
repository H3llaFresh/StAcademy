package by.vlfl.task7.di

import android.content.Context
import by.vlfl.task7.MainApp
import by.vlfl.task7.di.module.AppModule
import by.vlfl.task7.di.module.NetworkModule
import by.vlfl.task7.di.module.RatesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RatesModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(application: MainApp)
    fun mainComponent(): MainComponent.Factory
}