package by.vlfl.task6_mvvm_arch.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.vlfl.task6_mvvm_arch.R

class FragmentRateInfo : Fragment(R.layout.fragment_rate_info) {

    private val args: FragmentRateInfoArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currencyRussianName: TextView = view.findViewById(R.id.text_currency_ru)
        currencyRussianName.text = requireContext().getString(
            R.string.currency_RU_name_fragment_info,
            args.currencyRussianName
        )
        val currencyBelarusianName: TextView = view.findViewById(R.id.text_currency_by)
        currencyBelarusianName.text = requireContext().getString(
            R.string.currency_BY_name_fragment_info,
            args.currencyBelarusianName
        )
    }
}