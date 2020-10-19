package com.example.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ResultNumberAdapter : RecyclerView.Adapter<ResultNumberViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultNumberViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.result_numbers_item, parent, false)
        return ResultNumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultNumberViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(items: List<Int>) {
        this.items.let {
            if (it.size != 0) {
                it.clear()
            }
            it.addAll(items)
        }
        notifyDataSetChanged()
    }
}