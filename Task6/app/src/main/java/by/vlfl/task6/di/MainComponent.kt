package by.vlfl.task6.di

import by.vlfl.task6.view.FragmentMain
import by.vlfl.task6.view.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(fragment: FragmentMain)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
}