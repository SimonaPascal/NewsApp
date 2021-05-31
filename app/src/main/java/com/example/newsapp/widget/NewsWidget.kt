package com.example.newsapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.ListView
import android.widget.RemoteViews
import com.example.newsapp.R
import com.example.newsapp.adapters.ArticleAdapter
import com.example.newsapp.data.model.Article
import com.example.newsapp.services.JSONService
import org.json.JSONArray
import org.json.JSONObject

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewsWidgetConfigureActivity]
 */
class NewsWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = loadTitlePref(context, appWidgetId)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.news_widget)
    val intent = Intent(context, AdapterService::class.java)
    views.setRemoteAdapter(R.id.news_widget_list, intent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}