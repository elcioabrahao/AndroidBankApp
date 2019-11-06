package com.trantordev.androidbankapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.trantordev.androidbankapp.data.Login
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.ui.LoginListener
import kotlinx.coroutines.runBlocking


class LoginViewModel internal constructor(private val loginR: LoginRepository): ViewModel() {

    var user : String ? = null
    var password : String ? = null
    var loginListener : LoginListener? = null


    fun onLoginButtonClick(view: View){

        loginListener?.onStarted()
        if (user.isNullOrEmpty() || password.isNullOrEmpty()){
            loginListener?.onFailure("Incorrect email or password.")
            return
        }else{
            runBlocking {
                val loginResponse =  loginR.getUserInfo(Login(user!!,password!!))
                loginListener?.onSuccess(loginResponse)
            }

        }

    }


}
