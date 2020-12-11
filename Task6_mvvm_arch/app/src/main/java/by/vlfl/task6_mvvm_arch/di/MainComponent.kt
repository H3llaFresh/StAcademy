package by.vlfl.task6_mvvm_arch.di

import androidx.fragment.app.Fragment
import by.vlfl.task6_mvvm_arch.view.FragmentMain
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainFragment: FragmentMain)
}