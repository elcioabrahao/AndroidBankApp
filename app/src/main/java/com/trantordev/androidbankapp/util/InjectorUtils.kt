package com.trantordev.androidbankapp.util

import android.content.Context
import android.content.ContextWrapper
import androidx.lifecycle.LifecycleOwner
import com.trantordev.androidbankapp.data.ApiServices
import com.trantordev.androidbankapp.data.AppDatabase
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.viewmodel.LoginViewModelFactory


object InjectorUtils {


    private fun getLoginRepository(context: Context): LoginRepository {

        var ctx: Context = context

        while (ctx !is LifecycleOwner) {
            ctx = (context as ContextWrapper).baseContext
        }
        return LoginRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).loginDao(),
            AppDatabase.getInstance(context.applicationContext).clientAccountInfoDao(),
            ApiServices.getInstance(),
            ctx
        )
    }



    fun provideLoginViewModelFactory(
        context: Context
    ): LoginViewModelFactory {
        val repository = getLoginRepository(context)
        return LoginViewModelFactory(repository)
    }
}