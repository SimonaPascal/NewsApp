package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.newsapp.R


class FirstActivity : AppCompatActivity() {
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        init()
        loginButtonPressed()
        registerButtonPressed()
    }
    fun init(){
        this.registerButton = findViewById(R.id.register_button_first_page)
        this.loginButton = findViewById(R.id.login_button_first_page)
    }
    fun registerButtonPressed() = this.registerButton.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


    fun loginButtonPressed() = this.loginButton.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}