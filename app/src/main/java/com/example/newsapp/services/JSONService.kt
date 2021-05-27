package com.example.newsapp.services

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import java.net.URL


class JSONService:Service() {

    private var request = "https://gnews.io/api/v4/"
    private var token = "&token=49946991f1f8a607ec413657828cea30"
    private var url = ""
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val extras = intent.extras
        if(extras == null) {
            url = request + "top-headlines?country=ro" + token
        } else {
            val categoriesString = extras.get("categories").toString()
            val categories: List<String> = (categoriesString?.split(","))
            url = request + "search?q=";

            var index = 0
            for(item in categories)
            {
                if(index != 0)
                {
                    url = url + " OR "

                }
                index++
                url = url + item
            }

            url = url + "&country=ro" + token
        }

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
        val result = URL(url).readText()
        val parsedJSON: String = result
        intent.putExtra("JSON", parsedJSON)
        sendBroadcast(intent)
    }
}

