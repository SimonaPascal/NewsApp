package com.example.newsapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.database.DatabaseManager
import com.example.newsapp.databinding.ActivityLoginBinding
import com.example.newsapp.databinding.ActivityRegisterBinding
import com.example.newsapp.models.User
import com.example.newsapp.viewmodels.LoginViewModel
import com.example.newsapp.viewmodels.RegisterViewModel


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passwordConf: EditText
    private lateinit var phone: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var registerButton: Button
    private lateinit var genderRadioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        registerViewModel = ViewModelProvider(this)
            .get(RegisterViewModel::class.java)


        init()

        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer

            if (registerState.nameError != null) {
                userName.error = getString(registerState.nameError)
            }
            if (registerState.usernameError != null) {
                email.error = getString(registerState.usernameError)
            }
            if (registerState.passwordError != null) {
                password.error = getString(registerState.passwordError)
            }
            if (registerState.passwordError != null) {
                passwordConf.error = getString(registerState.passwordError)
            }
            if (registerState.phoneError != null) {
                phone.error = getString(registerState.phoneError)
            }
        })

        changeEditText(userName, email, password, passwordConf, userName, phone)
        //changeEditText(email, email, password, passwordConf, userName, phone)
        changeEditText(password, email, password, passwordConf, userName, phone)
        //changeEditText(passwordConf, email, password, passwordConf, userName, phone)
        //changeEditText(phone, email, password, passwordConf, userName, phone)



        registerButtonPressed()
    }

    fun init(){
        userName = findViewById(R.id.name_edit_text)
        email = findViewById(R.id.email_address_edit_text)
        password = findViewById(R.id.password_edit_text)
        passwordConf = findViewById(R.id.confirm_password_edit_text)
        phone = findViewById(R.id.phone_edit_text)
        radioGroup = findViewById(R.id.gender_radio_group)
        var selectedId = radioGroup.checkedRadioButtonId
        genderRadioButton = findViewById(selectedId)
        registerButton = findViewById(R.id.register_button_register_page)
    }
    fun changeEditText(editText: EditText, email: EditText, password: EditText, passwordConf: EditText, userName: EditText, phone: EditText){
        editText.afterTextChanged {
            registerViewModel.registerDataChanged(
                email.text.toString(),
                password.text.toString(),
                passwordConf.text.toString(),
                userName.text.toString(),
                phone.text.toString()

            )
        }

    }


    fun registerButtonPressed(){

        registerButton.setOnClickListener {
            val user = User(userName.text.toString(),email.text.toString(), password.text.toString(), phone.text.toString(), genderRadioButton.getText().toString().trim())
            Toast.makeText(
                this@RegisterActivity,
                user.toString(),
                Toast.LENGTH_SHORT
            ).show()
            if(!user.emailAddress.isEmpty() && !user.password.isEmpty() && !passwordConf.text.isEmpty() && !user.phone.isEmpty() && !user.name.isEmpty()){

                if(registerViewModel.register(user, passwordConf.text.toString(), this@RegisterActivity) == true){
                    val intent = Intent(this@RegisterActivity, NewsCategoryActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(
                        this@RegisterActivity,
                        this@RegisterActivity.getString(R.string.registration_failed_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }


    }

}


