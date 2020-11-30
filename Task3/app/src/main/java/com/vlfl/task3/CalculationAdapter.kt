package com.vlfl.task3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vlfl.task3.databinding.ResultNumbersItemBinding
import com.vlfl.task3.db.Calculation

class CalculationAdapter : RecyclerView.Adapter<CalculationViewHolder>() {
    val items = mutableListOf<Calculation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val binding = ResultNumbersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalculationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}