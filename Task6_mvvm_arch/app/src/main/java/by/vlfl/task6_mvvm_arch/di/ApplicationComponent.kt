package by.vlfl.task6_mvvm_arch.di

import android.content.Context
import by.vlfl.task6_mvvm_arch.MainApp
import by.vlfl.task6_mvvm_arch.di.module.AppModule
import by.vlfl.task6_mvvm_arch.di.module.NetworkModule
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

    fun inject(mainApp: MainApp)
    fun mainComponent(): MainComponent.Factory
}