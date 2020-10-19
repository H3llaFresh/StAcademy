package com.example.task1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.task1.Constants.BROADCAST_INTENT_KEY
import com.example.task1.Constants.LOG_TAG
import com.example.task1.Constants.SAVE_INSTANCE_KEY
import com.example.task1.Constants.SERVICE_INTENT_KEY


class MainActivity : AppCompatActivity() {

    private var n = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                n = getInt(SAVE_INSTANCE_KEY)
            }
        }
        n++
        Log.w(LOG_TAG, "$n")

        Intent(this, LeonardoNumberService::class.java).also { intent ->
            intent.putExtra(SERVICE_INTENT_KEY, n)
            startService(intent)
        }

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(numberReceiver, IntentFilter(Constants.BROADCAST_ACTION_NAME))
    }

    private val numberReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val number = intent.getIntExtra(BROADCAST_INTENT_KEY, 0)
            Log.d(LOG_TAG, "Leonardo number: $number")
            Toast.makeText(
                this@MainActivity,
                "Leonardo number: $number",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt(SAVE_INSTANCE_KEY, n)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(numberReceiver)
        Intent(this, LeonardoNumberService::class.java).also { intent ->
            stopService(intent)
        }
    }
}