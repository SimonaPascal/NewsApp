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
import com.example.newsapp.database.DatabaseManager
import com.example.newsapp.services.JSONService
import org.json.JSONArray
import org.json.JSONObject

class AdapterWidget(val context : Context) : RemoteViewsService.RemoteViewsFactory {

    private var articleList : ArrayList<Article> = ArrayList()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val database = DatabaseManager.getInstance(context)
        val articles = database.articleDao()?.getArticles()
        articleList = ArrayList(articles)
        //val author = "ProSport"
        //val title = "Team Of The Season Ciro Immobile: unul dintre cele mai ofensive carduri din FIFA 21!"
        //val description = "Seria SBC pare să fie din ce în ce mai populară în FIFA 21, EA Sports lansând în această perioadă o mulțime de carduri interesante. De la un design special și atribute crescute considerabil, cardurile au reușit să schimbe modul Ultimate Team și să le ofere gamerilor un conținut unic.\n" +
        //        "\n" +
        //        "Bineînțeles, evenimentele de genul reprezintă și o sursă importantă de venit pentru EA Sports. Cardurile sunt introduse în pachetele promoționale, iar unii gameri aleg să utilizeze bani reali pentru deblocarea acestora.\n" +
        //        "\n" +
        //        "Unul dintre cele mai bune SBC-uri vine din eSeria A și îi aparține lui Ciro Immobile, pe poziția de atacant central. Cu o viteză de 93, un șut de 96, pasă de 82 și dribling de 92, cardul are o serie de atribute foarte bune și poate face diferența în careul advers.\n" +
        //        "\n" +
        //        "SBC-ul costă în jur de 132.000 de monede FUT pe PlayStation, 149.000 pe Xbox și 137.000 pe PC. Dacă doriți să finalizați TOTS Immobile SBC, va trebui să prezentați două echipe diferite. Prima echipă trebuie să aibă 83 de calificative, cu minimum 75 de chimie, trebuie să aibă un jucător italian și cel puțin o carte Inform sau TOTS. A doua soluție necesită o echipă cu 84 de calificative cu minimum 70 de chimie, cel puțin o carte Inform sau TOTS și un jucător din eSerie A."
        //val url = "NoUrl"
        //val urlToImage = "NoImg"

        //val article = Article(author, title, description, url, urlToImage);
        //articleList.add(article)
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