package com.vlfl.task3

import androidx.recyclerview.widget.RecyclerView
import com.vlfl.task3.databinding.ResultNumbersItemBinding
import com.vlfl.task3.db.Calculation

class CalculationViewHolder(private val binding: ResultNumbersItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(calculation: Calculation) {
        val context = binding.root.context
        binding.textVariantResultItem.text = context
            .getString(R.string.text_variant_fragment_b_and_c, calculation.variant)
        binding.textTaskDescriptionResultItem.text = calculation.task
        binding.textCountDateResultItem.text = context
            .getString(R.string.text_date_fragment_c, calculation.date)
        binding.textResultNumber.text = context
            .getString(R.string.text_result_fragment_c, calculation.result)
    }
}