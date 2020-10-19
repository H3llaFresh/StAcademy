package com.example.task1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.task1.Constants.BROADCAST_ACTION_NAME
import com.example.task1.Constants.BROADCAST_INTENT_KEY
import com.example.task1.Constants.SERVICE_INTENT_KEY

class LeonardoNumberService : Service() {

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val n = intent.getIntExtra(SERVICE_INTENT_KEY, 0)
        val result = leonardoNumberCounter(n)
        Intent(BROADCAST_ACTION_NAME).also { broadcastIntent ->
            broadcastIntent.putExtra(BROADCAST_INTENT_KEY, result)
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun leonardoNumberCounter(number: Int): Int {
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
}
