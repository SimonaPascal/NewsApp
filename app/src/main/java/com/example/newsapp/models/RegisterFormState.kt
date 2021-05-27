package com.example.newsapp.models

data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val phoneError: Int? = null,
    val nameError: Int? = null,
    val isDataValid: Boolean = false
)