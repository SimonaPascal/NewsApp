package com.example.newsapp.services

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.URL
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class JSONService:Service() {

    private val request = "https://gnews.io/api/v4/search?q=covid&country=ro"
    private val token = "&token=49946991f1f8a607ec413657828cea30"
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mRunnable = Runnable { getJSON() }
        Thread(mRunnable).start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate() {
        if (isInternetWorking()) {
            Log.d("OK_INTERNET","Internect connection!")
        } else
            stopSelf()
    }

    private fun isInternetWorking(): Boolean {
        val command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getJSON() {
        val intent = Intent("ACTION_JSON")
        val url = request + token
        try {
            val urlResponse = URL(url).readText()
        } catch (e : Exception) {
            e.printStackTrace()
        }
        val result = URL(url).readText()
        val parsedJSON: String = result
        intent.putExtra("JSON", parsedJSON)
        sendBroadcast(intent)
    }
}

