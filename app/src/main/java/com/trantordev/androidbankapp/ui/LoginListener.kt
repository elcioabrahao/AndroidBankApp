package com.trantordev.androidbankapp.ui

import androidx.lifecycle.LiveData

interface LoginListener {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message: Int)

}