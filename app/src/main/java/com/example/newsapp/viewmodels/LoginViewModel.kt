package com.example.newsapp.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import com.example.newsapp.R
import com.example.newsapp.activities.NewsCategoryActivity
import com.example.newsapp.database.DatabaseManager
import com.example.newsapp.models.User
import com.example.newsapp.models.LoginFormState

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(email: String, password: String, context: Context):Boolean{
        val database = DatabaseManager.getInstance(context)
        val user: User? = database.userDao()?.getUserByEmailAndPassword(email, password)
        if(user != null){

            return true
        }

        return false
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }


    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}