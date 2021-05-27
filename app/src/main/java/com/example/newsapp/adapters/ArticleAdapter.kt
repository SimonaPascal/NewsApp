package com.example.newsapp.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapp.R
import com.example.newsapp.data.model.Article
import java.net.URL

class ArticleAdapter(private val context: Context,
                    private val dataSource: ArrayList<Article>):BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.news_item, parent, false)

        val author = rowView.findViewById(R.id.author) as TextView
        val title = rowView.findViewById(R.id.title) as TextView
        val description = rowView.findViewById(R.id.description) as TextView

        val article = getItem(position) as Article

        author.text = article.author
        title.text = article.title
        description.text = article.description

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount():Int {
        return dataSource.size
    }
}