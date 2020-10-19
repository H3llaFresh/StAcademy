package com.example.task2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task2.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null

    private val binding get() = _binding!!

    private var listener: OnNextButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnNextButtonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            when (val textInput = binding.numberInput.text.toString()) {
                "" -> binding.numberInputWrapper.error = getString(R.string.enter_index_of_number)
                "0" -> binding.numberInputWrapper.error = getString(R.string.enter_not_null_index)
                else -> {
                    binding.numberInputWrapper.error = null
                    val leonardoNumberIndex = textInput.toInt()
                    listener?.onNextButtonClicked(leonardoNumberIndex)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNextButtonClickListener {
        fun onNextButtonClicked(leonardoNumberIndex: Int)
    }
}
