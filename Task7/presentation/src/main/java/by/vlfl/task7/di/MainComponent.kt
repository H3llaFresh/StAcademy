package by.vlfl.task7.di

import by.vlfl.task7.view.FragmentMain
import by.vlfl.task7.view.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(fragment: FragmentMain)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
}