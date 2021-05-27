package com.example.newsapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.example.newsapp.R


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        var drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        var nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
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
        }
        return false
    }
}
