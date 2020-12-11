package by.vlfl.task6_mvvm.di

import by.vlfl.task6_mvvm.view.FragmentMain
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(fragment: FragmentMain)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
}