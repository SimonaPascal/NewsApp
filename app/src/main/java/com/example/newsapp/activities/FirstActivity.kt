package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.newsapp.R
import com.example.newsapp.ui.login.LoginActivity

class FirstActivity : AppCompatActivity() {
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var newsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        init()
        loginButtonPressed()
        registerButtonPressed()
        newsButtonPressed()
    }
    fun init(){
        this.registerButton = findViewById(R.id.register_button_first_page)
        this.loginButton = findViewById(R.id.login_button_first_page)
        this.newsButton = findViewById(R.id.news_first_page)
    }
    fun registerButtonPressed() = this.registerButton.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


    fun loginButtonPressed() = this.loginButton.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun newsButtonPressed() = this.newsButton.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}