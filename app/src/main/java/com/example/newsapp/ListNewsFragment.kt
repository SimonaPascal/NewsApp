package com.example.newsapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.ArticleAdapter
import com.example.newsapp.data.model.Article
import com.example.newsapp.services.JSONService
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.List

class ListNewsFragment : Fragment() {


    val list = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //thisContext = this.requireActivity()
        val intent = Intent(context, JSONService::class.java)
        intent.putExtra("categories", "covid,musk");
        context?.startService(intent)
        Toast.makeText(context, "Aducem datele!", Toast.LENGTH_LONG).show();

    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(receptorJSON)
    }

    override fun onResume() {
        super.onResume()
        context?.registerReceiver(receptorJSON, IntentFilter("ACTION_JSON"))
    }

    private val receptorJSON = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            var json = intent?.getStringExtra("JSON")
            val articlesListView = view?.findViewById<ListView>(R.id.articles_list_view);
            var jsonObject = JSONObject(json)
            val articoleString: String = jsonObject.getString("articles")
            val articoleArray = JSONArray(articoleString)
            if(articoleArray != null) {
                for(i in 0..articoleArray.length()-1) {
                    var item = articoleArray.getJSONObject(i);
                    var source = JSONObject(item.getString("source"))
                    var author = source.getString("name")
                    var title = item.getString("title")
                    var description = item.getString("description")
                    var url = item.getString("url")
                    var urlToImage = item.getString("image")

                    val article = Article(author, title, description, url, urlToImage);
                    list.add(article)
                }

                val articleAdapter = ArticleAdapter(context, list as ArrayList<Article>)
                articlesListView?.adapter = articleAdapter
            }
        }
    }
}

