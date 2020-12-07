package by.vlfl.task5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import by.vlfl.task5.R
import by.vlfl.task5.common.BaseFragment
import by.vlfl.task5.databinding.MainScreenBinding
import by.vlfl.task5.vm.MainViewModel

class FragmentMain : BaseFragment<MainViewModel>() {

    private val mViewModel: MainViewModel? by lazy {
        MainViewModel()
    }

    override fun getViewModel(): MainViewModel? {
        return mViewModel
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
        mViewModel?.loadRates()
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