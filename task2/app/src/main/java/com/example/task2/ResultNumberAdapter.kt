package com.example.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.databinding.ResultNumbersItemBinding

class ResultNumberAdapter : RecyclerView.Adapter<ResultNumberViewHolder>() {

    val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultNumberViewHolder {
        val binding =
            ResultNumbersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultNumberViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}