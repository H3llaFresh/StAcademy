package com.example.task2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.databinding.ResultNumbersItemBinding

class ResultNumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textView: TextView = view.findViewById(R.id.text_result_number)

    fun bind(resultNumber: Int) {
        textView.text = resultNumber.toString()
    }
}