package com.trantordev.androidbankapp.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.trantordev.androidbankapp.R
import com.trantordev.androidbankapp.data.Login
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.ui.LoginListener
import com.trantordev.androidbankapp.util.ValidationUtils
import kotlinx.coroutines.runBlocking


class LoginViewModel internal constructor(private val loginR: LoginRepository
) : ViewModel() {

    var user: String? = null
    var password: String? = null
    var loginListener: LoginListener? = null


    fun onLoginButtonClick(view: View) {

        loginListener?.onStarted()

        if (user.isNullOrEmpty()) {
            loginListener?.onFailure(R.string.validation_user_not_empity)
            return
        } else {
            if (ValidationUtils.isNumber(user)) {
                if (!ValidationUtils.isValidCPF(user!!)) {
                    loginListener?.onFailure(R.string.validation_user_cpf_must_be_valid)
                    return
                }
            } else {
                if (!ValidationUtils.isValidEmail(user!!)) {
                    loginListener?.onFailure(R.string.validation_user_email_must_be_valid)
                    return
                }
            }
            if (password.isNullOrEmpty()) {
                loginListener?.onFailure(R.string.validation_password_must_not_be_empity)
                return
            } else {
                if (!ValidationUtils.isValidPassword(password!!)) {
                    loginListener?.onFailure(R.string.validation_password_valid)
                    return
                }
            }
        }

        runBlocking {
            val loginResponse = loginR.getUserInfo(Login(user!!, password!!))
            loginListener?.onSuccess(loginResponse)
        }


    }


}
