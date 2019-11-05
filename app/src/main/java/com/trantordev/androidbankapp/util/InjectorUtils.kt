package com.trantordev.androidbankapp.util

import android.content.Context
import com.trantordev.androidbankapp.data.AppDatabase
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.viewmodel.LoginViewModelFactory

object InjectorUtils {


    private fun getLoginRepository(context: Context): LoginRepository {
        return LoginRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).loginDao())
    }



    fun provideLoginViewModelFactory(
        context: Context
    ): LoginViewModelFactory {
        val repository = getLoginRepository(context)
        return LoginViewModelFactory(repository)
    }
}