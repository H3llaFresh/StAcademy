package by.vlfl.task7_mvvm_arch.di

import by.vlfl.task7_mvvm_arch.view.FragmentMain
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainFragment: FragmentMain)
}