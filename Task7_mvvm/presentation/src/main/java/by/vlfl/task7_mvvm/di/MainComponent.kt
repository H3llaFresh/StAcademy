package by.vlfl.task7_mvvm.di

import by.vlfl.task7_mvvm.view.FragmentMain
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(fragment: FragmentMain)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
}