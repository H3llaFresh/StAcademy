package com.vlfl.task3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vlfl.task3.CalculationAdapter
import com.vlfl.task3.R
import com.vlfl.task3.databinding.FragmentCBinding
import com.vlfl.task3.db.Calculation
import com.vlfl.task3.db.CalculationRoomDatabase
import com.vlfl.task3.network.NetworkApiService
import kotlinx.coroutines.launch

class FragmentC : Fragment() {

    private var _binding: FragmentCBinding? = null

    private val binding get() = _binding!!

    private val _calculations: MutableList<Calculation> = mutableListOf()

    private val calculations: List<Calculation> = _calculations

    private val calculationAdapter = CalculationAdapter()

    private val args: FragmentCArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name?:""
        val prevDestination = view.findNavController().previousBackStackEntry
        if  (prevDestination?.destination?.label == "FragmentA") {
                setDataFromDatabase()
            } else {
                setDataFromNetwork(name)
            }
        if (_calculations.isEmpty()) {
            Toast.makeText(context, "Нет Интырнета - нет данных:(", Toast.LENGTH_SHORT).show()
        }
        calculationAdapter.items.addAll(calculations)
        binding.recyclerViewResult.apply {
            adapter = calculationAdapter
            addItemDecoration(
                    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
        binding.buttonClose.setOnClickListener {
            findNavController().popBackStack(R.id.fragment_a, false)
        }
    }

    private fun setDataFromDatabase() {
        val calculationDao = CalculationRoomDatabase
                 .getDatabase(requireContext().applicationContext).calculationDao()
        lifecycleScope.launch {
            _calculations.addAll(calculationDao.getAllCalculations())
        }
    }

    private fun setDataFromNetwork(name: String) {
        lifecycleScope.launch {
            _calculations.addAll(NetworkApiService.NetworkApi.getCalculationsFromNetwork(name))
        }
    }
}