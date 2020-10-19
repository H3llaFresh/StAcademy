package com.example.task2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.databinding.ResultNumbersItemBinding

class ResultNumberViewHolder(private val binding: ResultNumbersItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(resultNumber: Int) {
        binding.textResultNumber.text = resultNumber.toString()
    }
}