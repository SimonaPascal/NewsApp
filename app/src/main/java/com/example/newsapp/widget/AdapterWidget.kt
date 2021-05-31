package com.example.newsapp.widget

import android.content.Context
import android.content.Intent
import android.widget.ListView
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.newsapp.ListNewsFragment
import com.example.newsapp.R
import com.example.newsapp.adapters.ArticleAdapter
import com.example.newsapp.data.model.Article
import com.example.newsapp.services.JSONService
import org.json.JSONArray
import org.json.JSONObject

class AdapterWidget(val context : Context) : RemoteViewsService.RemoteViewsFactory {

    private var articleList : ArrayList<Article> = ArrayList()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val author = "HardcodedTestAuthor"
        val title = "HardcodedTestTitle"
        val description = "HardcodedTestDescription"
        val url = "NoUrl"
        val urlToImage = "NoImg"

        val article = Article(author, title, description, url, urlToImage);
        articleList.add(article)
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return articleList.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val article = articleList[position]
        val views = RemoteViews(context.packageName, R.layout.news_item_widget)
        views.setTextViewText(R.id.tvSource, article.author)
        views.setTextViewText(R.id.tvTitle, article.title)
        views.setTextViewText(R.id.tvDescription, article.description)
        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}