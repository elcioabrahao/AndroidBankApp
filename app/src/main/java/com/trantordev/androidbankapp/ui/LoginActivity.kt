package com.trantordev.androidbankapp.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import com.trantordev.androidbankapp.R
import com.trantordev.androidbankapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
    }
}
