package com.vlfl.task3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.vlfl.task3.R
import com.vlfl.task3.databinding.FragmentBBinding
import com.vlfl.task3.db.Calculation
import com.vlfl.task3.db.CalculationRoomDatabase
import com.vlfl.task3.network.CalculationNetwork
import com.vlfl.task3.network.NetworkApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null

    private val binding get() = _binding!!

    private val args: FragmentBArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name
        val variant = args.variant
        val parameter = args.parameter
        val description = args.description.replace("N", parameter.toString())
        binding.textName.text = getString(R.string.text_user_name_fragment_b, name)
        binding.textVariant.text = getString(R.string.text_variant_fragment_b_and_c, variant)
        binding.textTaskDescription.text = getString(R.string.text_task_description_fragment_b, description)
        binding.textTaskParameter.text = getString(R.string.text_parameter_fragment_b, parameter)
        binding.buttonCalculate.setOnClickListener {
            val result = calculateLeonardoNumber(parameter)
            val date = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                .format(Calendar.getInstance().time)
            val action = FragmentBDirections.actionFragmentBToFragmentC(name)
            val calculation = Calculation(
                variant = variant,
                task = description,
                date =  date,
                result = result
            )
            lifecycleScope.launch {
                if (NetworkApiService.NetworkApi.sendCalculationsToNetwork(name,
                            CalculationNetwork(variant, description, date, result))) {
                    insertCalculationToDatabase(calculation)
                }
            }
            view.findNavController().navigate(action)
        }
    }

    private fun calculateLeonardoNumber(number: Int): Int {
        var firstLeoNumber = 1
        var secondLeoNumber = 1
        var result = 1
        for (i in 3..number) {
            result = firstLeoNumber + secondLeoNumber + 1
            secondLeoNumber = firstLeoNumber
            firstLeoNumber = result
        }
        return result
    }

    private suspend fun insertCalculationToDatabase(calculation: Calculation) {
        val calculationDao = CalculationRoomDatabase
            .getDatabase(requireContext().applicationContext).calculationDao()
        withContext(Dispatchers.IO) {
            calculationDao.insertCalculation(calculation)
        }
    }
}