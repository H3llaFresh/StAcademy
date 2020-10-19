package com.example.task2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task2.Constants.FILE_NAME
import com.example.task2.Constants.LEONARDO_NUMBER_KEY
import com.example.task2.databinding.FragmentCBinding
import java.io.File

class FragmentC : Fragment(R.layout.fragment_c) {

    private var _binding: FragmentCBinding? = null

    private val binding get() = _binding!!

    private val itemAdapter = ResultNumberAdapter()

    private val itemsLayoutManager = LinearLayoutManager(context)

    private var numbersList: MutableList<Int> = mutableListOf()

    private var listener: OnCloseButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnCloseButtonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newLeonardoNumber = arguments?.getInt(LEONARDO_NUMBER_KEY) ?: 0

        val numbersFile = File(requireContext().filesDir.toString(), FILE_NAME)

        if (numbersFile.exists()) {
            val fileContent = readFromFile()
                .trimIndent()
                .split(", ")
                .map { it.toInt() }

            numbersList.addAll(fileContent)
        }

        if (savedInstanceState == null) {
            numbersList.add(0, newLeonardoNumber)
            writeToFile(numbersList.joinToString(", "))
        }

        binding.recyclerViewResult.apply {
            adapter = itemAdapter
            layoutManager = itemsLayoutManager
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
        itemAdapter.setData(numbersList)

        binding.buttonClose.setOnClickListener {
            listener?.onCloseButtonClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun createInstance(leonardoNumberIndex: Int): FragmentC {
            return FragmentC().apply {
                arguments = bundleOf(
                    LEONARDO_NUMBER_KEY to leonardoNumberIndex
                )
            }
        }
    }

    private fun writeToFile(data: String) {

        val path = requireContext().filesDir

        if (!path.exists()) {
            path.mkdirs()
        }
        context?.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it?.write(data.toByteArray())
        }
    }

    private fun readFromFile(): String {
        return requireContext().openFileInput(FILE_NAME).bufferedReader()
            .useLines { lines ->
                lines.fold("") { some, text ->
                    "$some $text"
                }
            }
    }

    interface OnCloseButtonClickListener {
        fun onCloseButtonClicked()
    }
}