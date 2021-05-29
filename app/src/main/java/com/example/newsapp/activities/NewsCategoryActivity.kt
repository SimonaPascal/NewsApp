package com.example.newsapp.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.newsapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class NewsCategoryActivity : AppCompatActivity() {
    private lateinit var sportsButton: Button
    private lateinit var healthButton: Button
    private lateinit var scienceButton: Button
    private lateinit var entertainmentButton: Button
    private lateinit var businessButton: Button
    private lateinit var generalButton: Button
    private lateinit var continueButton: Button
    private var contor = 0
    private val categoryList: ArrayList<String> = ArrayList()
    var fragmentTransaction: FragmentTransaction? = null
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

        continueButton.setOnClickListener {
            if(contor < 3){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert")
                builder.setMessage("Please select 3 topics!!")
                builder.setPositiveButton(android.R.string.ok) { dialog, which ->

                }
                builder.show()

            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putStringArrayListExtra("categories", categoryList)
                startActivity(intent)
            }


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
        continueButton = findViewById(R.id.continue_button)
    }



}