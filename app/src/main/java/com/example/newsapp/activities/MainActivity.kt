package com.example.newsapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.example.newsapp.R
import com.example.newsapp.viewmodels.Observable
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var navController: NavController
    private lateinit var viewModel: Observable
    private lateinit var appBarConfiguration: AppBarConfiguration
    var categories: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        var drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        var nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

        val intent = intent
        categories = intent.getStringArrayListExtra("categories") as ArrayList<String>
        viewModel = this.run {
            ViewModelProviders.of(this).get(Observable::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.data.value = categories


    }

    override fun onSupportNavigateUp(): Boolean {
        var drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout);
        return NavigationUI.navigateUp(navController, drawer_layout);
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout);
        drawer_layout.closeDrawers()

        when (item.itemId) {
            R.id.nav_lista_stiri -> {
                navController.navigate(R.id.nav_list_news)

                return true
            }
            R.id.nav_maps ->{
                val intent = Intent(this, MapsActivity::class.java).apply {
                }
                startActivity(intent)

                return true
            }
        }
        return false
    }



}
