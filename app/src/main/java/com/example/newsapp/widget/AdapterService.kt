package com.example.newsapp.widget

import android.content.Intent
import android.widget.RemoteViewsService

class AdapterService :RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return AdapterWidget(applicationContext)
    }
}