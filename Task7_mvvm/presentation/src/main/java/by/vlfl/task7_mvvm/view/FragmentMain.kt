 package by.vlfl.task7_mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import by.vlfl.task7_mvvm.MainApp
import by.vlfl.task7_mvvm.R
import by.vlfl.task7_mvvm.common.BaseFragment
import by.vlfl.task7_mvvm.databinding.MainScreenBinding
import by.vlfl.task7_mvvm.vm.MainViewModel
import javax.inject.Inject

 class FragmentMain: BaseFragment<MainViewModel>() {

     @Inject
     lateinit var mViewModel: MainViewModel

     override fun getViewModel(): MainViewModel {
         return mViewModel
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainComponent = (activity?.application as MainApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
    }

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         val binding: MainScreenBinding by lazy {
             DataBindingUtil.inflate(inflater,R.layout.fragment_main ,container, false)
         }
         binding.vm = mViewModel
         mViewModel.loadRates()
         return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         val aboutButton: ImageView = view.findViewById(R.id.about_button)
         aboutButton.setOnClickListener {
             val action = FragmentMainDirections.actionFragmentMainToFragmentAbout()
             findNavController().navigate(action)
         }
     }
}