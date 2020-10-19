package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.task2.Constants.FRAGMENT_BACK_STACK_NAME

class MainActivity : AppCompatActivity(R.layout.activity_main),
    FragmentA.OnNextButtonClickListener,
    FragmentB.OnOkButtonClickListener,
    FragmentC.OnCloseButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, FragmentA())
                .commit()
        }
    }

    override fun onNextButtonClicked(leonardoNumberIndex: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FragmentB.createInstance(leonardoNumberIndex))
            .addToBackStack(FRAGMENT_BACK_STACK_NAME)
            .commit()
    }

    override fun onOkButtonClicked(leonardoNumber: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FragmentC.createInstance(leonardoNumber))
            .addToBackStack(FRAGMENT_BACK_STACK_NAME)
            .commit()
    }

    override fun onCloseButtonClicked() {
        supportFragmentManager
            .popBackStack(FRAGMENT_BACK_STACK_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}