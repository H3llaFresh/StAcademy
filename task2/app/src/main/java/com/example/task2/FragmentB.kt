package com.example.task2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.task2.Constants.LEONARDO_NUMBER_INDEX_KEY
import com.example.task2.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null

    private val binding get() = _binding!!

    private var okButtonListener: OnOkButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        okButtonListener = context as OnOkButtonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstLeonardNumber = requireArguments().getInt(LEONARDO_NUMBER_INDEX_KEY)

        binding.enteredLeonardoNumber.text =
            getString(R.string.index_of_leonardo_number, convertToOrdinal(firstLeonardNumber))

        binding.buttonOk.setOnClickListener {
            val leonardoNumber = calculateLeonardoNumber(firstLeonardNumber)
            okButtonListener?.onOkButtonClicked(leonardoNumber)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    private fun convertToOrdinal(number: Int): String {
        val numberToString = number.toString()
        return when (numberToString.last()) {
            '1' -> numberToString + "st"
            '2' -> numberToString + "nd"
            '3' -> numberToString + "rd"
            else -> numberToString + "th"
        }
    }

    companion object {
        fun createInstance(leonardoNumberIndex: Int): FragmentB {
            return FragmentB().apply {
                arguments = bundleOf(
                    LEONARDO_NUMBER_INDEX_KEY to leonardoNumberIndex
                )
            }
        }
    }

    interface OnOkButtonClickListener {
        fun onOkButtonClicked(leonardoNumber: Int)
    }
}