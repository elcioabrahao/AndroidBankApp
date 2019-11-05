package com.trantordev.androidbankapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.ui.LoginListener

class LoginViewModel internal constructor(loginRepository: LoginRepository): ViewModel() {

    var email : String ? = null
    var password : String ? = null
    var loginListener : LoginListener? = null

    fun onLoginButtonClick(view: View){

        loginListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            loginListener?.onFailure("Incorrect email or password.")
            return
        }else{
            // fetching live data from UserRepository
            //val loginResponse = LoginRepository().userLogin( email !!, password !!)

            // Success listener
            //loginListener?.onSuccess(loginResponse)

        }

    }


}
