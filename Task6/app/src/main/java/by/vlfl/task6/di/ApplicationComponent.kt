package by.vlfl.task6.di

import android.content.Context
import by.vlfl.task6.MainApp
import by.vlfl.task6.di.module.AppModule
import by.vlfl.task6.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(application: MainApp)
    fun mainComponent(): MainComponent.Factory
}