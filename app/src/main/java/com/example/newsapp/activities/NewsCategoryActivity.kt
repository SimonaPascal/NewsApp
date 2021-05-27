package com.example.newsapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.ButtonBarLayout
import androidx.core.content.ContextCompat
import com.example.newsapp.R

class NewsCategoryActivity : AppCompatActivity() {
    private lateinit var sportsButton: Button
    private lateinit var healthButton: Button
    private lateinit var scienceButton: Button
    private lateinit var entertainmentButton: Button
    private lateinit var businessButton: Button
    private lateinit var generalButton: Button
    private var contor = 0
    private val categoryList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_category)
        init()


        sportsButton.setOnClickListener {
          setButtonColor(sportsButton, "SPORTS")

        }

        entertainmentButton.setOnClickListener {
            setButtonColor(entertainmentButton, "ENTERTAINMENT")
        }

        generalButton.setOnClickListener {
            setButtonColor(generalButton, "GENERAL")
        }

        businessButton.setOnClickListener {
            setButtonColor(businessButton, "BUSINESS")
        }

        healthButton.setOnClickListener {
            setButtonColor(healthButton, "HEALTH")
        }

        scienceButton.setOnClickListener {
            setButtonColor(scienceButton, "SCIENCE")
        }
    }

    fun setButtonColor(button: Button, category: String){

        if(contor <3 && button.isSelected == false){
            button.setBackgroundColor(Color.parseColor("#FF00ACC1"))
            categoryList.add(category)
            contor++
            button.isSelected = true

        }else if(contor < 3 && button.isSelected == true){
            button.setBackgroundColor(Color.parseColor("#FF6200EE"))
            categoryList.remove(category)
            contor--
            button.isSelected = false
        }else if(contor == 3){
            button.setBackgroundColor(Color.parseColor("#FF6200EE"))
            categoryList.remove(category)
            contor--
            button.isSelected = false
        }
    }

    fun init(){
        sportsButton = findViewById(R.id.sports_button)
        healthButton = findViewById(R.id.health_button)
        scienceButton = findViewById(R.id.science_button)
        entertainmentButton = findViewById(R.id.entertainment_button)
        businessButton = findViewById(R.id.business_button)
        generalButton = findViewById(R.id.general_button)
    }
}