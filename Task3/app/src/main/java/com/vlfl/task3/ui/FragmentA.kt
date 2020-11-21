package com.vlfl.task3.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vlfl.task3.Constants.APP_PREFERENCES
import com.vlfl.task3.Constants.APP_PREFERENCES_VARIANT
import com.vlfl.task3.Constants.APP_PREFERENCES_TASK_DESCRIPTION
import com.vlfl.task3.R
import com.vlfl.task3.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null

    private val binding get() = _binding!!

    private var _sharedPrefs: SharedPreferences? = null

    private val sharedPrefs get() = _sharedPrefs!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _sharedPrefs = requireContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
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
        if (sharedPrefs.contains(APP_PREFERENCES_VARIANT)) {
            binding.inputVariant.setText(sharedPrefs.getString(APP_PREFERENCES_VARIANT, ""))
            binding.inputName.nextFocusDownId = R.id.input_description
        }
        if (sharedPrefs.contains(APP_PREFERENCES_TASK_DESCRIPTION)) {
            binding.inputDescription.setText(
                sharedPrefs.getString(
                    APP_PREFERENCES_TASK_DESCRIPTION,
                    ""
                )
            )
            binding.inputName.nextFocusDownId = R.id.input_parameters
        }

        binding.buttonToFragmentB.setOnClickListener {
            val nameInputText = binding.inputName.text.toString()
            val variantInputText = binding.inputVariant.text.toString()
            val descriptionInputText = binding.inputDescription.text.toString()
            val parametersInputText = binding.inputParameters.text.toString()

            val correctInputs = validateInputFields(
                nameInputText,
                variantInputText,
                descriptionInputText,
                parametersInputText
            )

            if (correctInputs) {
                if (sharedPrefs.getString(
                        APP_PREFERENCES_VARIANT,
                        ""
                    ) != variantInputText
                ) {
                    sharedPrefs.edit()
                        .putString(APP_PREFERENCES_VARIANT, variantInputText).apply()
                }
                if (sharedPrefs.getString(
                        APP_PREFERENCES_TASK_DESCRIPTION,
                        ""
                    ) != descriptionInputText
                ) {
                    sharedPrefs.edit()
                        .putString(APP_PREFERENCES_TASK_DESCRIPTION, descriptionInputText)
                        .apply()
                }
                val action = FragmentADirections.actionFragmentAToFragmentB(
                    nameInputText,
                    variantInputText.toInt(),
                    descriptionInputText,
                    parametersInputText.toInt()
                )
                requireView().findNavController().navigate(action)
            }
        }

        binding.buttonToFragmentC.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentC(null)
            requireView().findNavController().navigate(action)
        }

        binding.buttonToFragmentD.setOnClickListener {
            val action = FragmentADirections.actionFragmentAToFragmentD()
            requireView().findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateInputFields(
        nameInputText: String,
        variantInputText: String,
        descriptionInputText: String,
        parametersInputText: String
    ): Boolean {
        var noInputErrors = true
        if (nameInputText.isEmpty()) {
            binding.wrapperInputName.error = getString(R.string.text_error_input_field)
            binding.inputName.doAfterTextChanged { binding.wrapperInputName.error = "" }
            noInputErrors = false
        }

        if (variantInputText.isEmpty()) {
            binding.wrapperInputVariant.error = getString(R.string.text_error_input_field)
            binding.inputVariant.doAfterTextChanged { binding.wrapperInputVariant.error = "" }
            if (noInputErrors) {
                noInputErrors = false
            }
        }

        if (descriptionInputText.isEmpty()) {
            binding.wrapperInputDescription.error = getString(R.string.text_error_input_field)
            binding.inputDescription.doAfterTextChanged {
                binding.wrapperInputDescription.error = ""
            }
            if (noInputErrors) {
                noInputErrors = false
            }
        }

        if (parametersInputText.isEmpty()) {
            binding.wrapperInputParameters.error = getString(R.string.text_error_input_field)
            binding.inputParameters.doAfterTextChanged { binding.wrapperInputParameters.error = "" }
            if (noInputErrors) {
                noInputErrors = false
            }
        }
        return noInputErrors
    }
}

