package com.example.newsapp.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.ListView
import android.widget.RemoteViewsService
import com.example.newsapp.R
import com.example.newsapp.adapters.ArticleAdapter
import com.example.newsapp.data.model.Article
import com.example.newsapp.services.JSONService
import org.json.JSONArray
import org.json.JSONObject

class AdapterService :RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return AdapterWidget(applicationContext)
    }
}