package com.example.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class Observable: ViewModel() {
    val data = MutableLiveData<ArrayList<String>>()

    fun data(list: ArrayList<String>) {
        list.also { data.value = it }
    }
}