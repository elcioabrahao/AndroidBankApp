package com.trantordev.androidbankapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.trantordev.androidbankapp.R
import com.trantordev.androidbankapp.databinding.ActivityLoginBinding
import com.trantordev.androidbankapp.databinding.ActivityStatementBinding

class StatementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityStatementBinding>(this, R.layout.activity_statement)
    }
}