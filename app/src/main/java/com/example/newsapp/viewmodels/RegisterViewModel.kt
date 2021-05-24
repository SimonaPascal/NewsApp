package com.example.newsapp.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.R
import com.example.newsapp.activities.NewsCategoryActivity
import com.example.newsapp.database.DatabaseManager
import com.example.newsapp.models.LoginFormState
import com.example.newsapp.models.RegisterFormState
import com.example.newsapp.models.User

class RegisterViewModel: ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    fun register(user: User, passwordConf: String, context: Context):Boolean{
        val database = DatabaseManager.getInstance(context)
        if (user.password == passwordConf) {

            val i: Long? = database.userDao()?.insert(user)
            if(database.userDao()?.getUser(i!!).toString() != null){
                return true
            }else{
                return false
            }
        } else {
            Toast.makeText(
                context,
                "Password is not matching",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    fun registerDataChanged(username: String, password: String, name: String, phone:String, passwordConf: String) {
        if (!isUserNameValid(username)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        }else if (!isPasswordValid(passwordConf)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        }else if(!isNameValid(name)){
            _registerForm.value = RegisterFormState(nameError = R.string.invalid_name)
        } else if(!isPhoneValid(phone)){
            _registerForm.value = RegisterFormState(phoneError = R.string.invalid_phone)
        }else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }


    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 10
    }

}