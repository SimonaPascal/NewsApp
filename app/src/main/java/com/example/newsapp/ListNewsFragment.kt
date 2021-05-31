package com.example.newsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.activities.MainActivity
import com.example.newsapp.adapters.ArticleAdapter
import com.example.newsapp.data.model.Article
import com.example.newsapp.database.DatabaseManager
import com.example.newsapp.services.JSONService
import com.example.newsapp.viewmodels.Observable
import org.json.JSONArray
import org.json.JSONObject


class ListNewsFragment : Fragment() {


    val list = mutableListOf<Article>()
    var categoryList: ArrayList<String> = ArrayList()
    private lateinit var viewModel: Observable
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
        //val values = requireArguments().getStringArrayList("categories")
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(Observable::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.data.observe(viewLifecycleOwner, Observer {

            val intent = Intent(context, JSONService::class.java)
            intent.putExtra("categories", viewModel.data.value!![0] +  "," + viewModel.data.value!![1] + "," + viewModel.data.value!![2]);
            context?.startService(intent)
        })

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
            val database = DatabaseManager.getInstance(context)
            database.articleDao()?.deleteArticles()
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
                    database.articleDao()?.insert(article)
                    list.add(article)
                }

                val articleAdapter = ArticleAdapter(context, list as ArrayList<Article>)
                articlesListView?.adapter = articleAdapter
            }
        }
    }


}

