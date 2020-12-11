package by.vlfl.task6_mvvm_arch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.vlfl.task6_mvvm_arch.MainApp
import by.vlfl.task6_mvvm_arch.R
import by.vlfl.task6_mvvm_arch.vm.MainViewModel
import by.vlfl.task6_mvvm_arch.databinding.MainScreenBinding
import javax.inject.Inject

class FragmentMain : Fragment() {

    @Inject
    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainComponent = (activity?.application as MainApp).applicationComponent.mainComponent().create()
        mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainScreenBinding by lazy {
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        }
        binding.vm = mViewModel
        mViewModel.loadRates()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val aboutButton: ImageView = view.findViewById(R.id.about_button)
        val customAdapter = CustomAdapter()
        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = customAdapter
            val mLayoutManager = LinearLayoutManager(context)
            layoutManager = mLayoutManager

            val mDividerItemDecoration = DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
            addItemDecoration(mDividerItemDecoration)
        }

        mViewModel.rates.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            customAdapter.replaceAll(it)
        })

        aboutButton.setOnClickListener {
            val action = FragmentMainDirections.actionFragmentMainToFragmentAbout()
            findNavController().navigate(action)
        }
    }
}