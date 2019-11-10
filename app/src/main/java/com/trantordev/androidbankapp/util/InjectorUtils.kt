package com.trantordev.androidbankapp.util

import android.content.Context
import com.trantordev.androidbankapp.data.network.ApiServices
import com.trantordev.androidbankapp.data.database.AppDatabase
import com.trantordev.androidbankapp.data.LoginRepository
import com.trantordev.androidbankapp.data.StatementRepository
import com.trantordev.androidbankapp.viewmodel.LoginViewModelFactory
import com.trantordev.androidbankapp.viewmodel.StatementViewModelFactory


object InjectorUtils {


    private fun getLoginRepository(context: Context): LoginRepository {

        return LoginRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).loginDao(),
            AppDatabase.getInstance(context.applicationContext).clientAccountInfoDao(),
            ApiServices.getInstance()
        )
    }

    private fun getStatementRepository(context: Context): StatementRepository {
        return StatementRepository.getInstance(
            ApiServices.getInstance(),
            AppDatabase.getInstance(context.applicationContext).loginDao(),
            AppDatabase.getInstance(context.applicationContext).clientAccountInfoDao())
    }

    fun provideLoginViewModelFactory(
        context: Context
    ): LoginViewModelFactory {

        val repository = getLoginRepository(context)
        return LoginViewModelFactory(repository)
    }

    fun provideStatementListViewModelFactory(context: Context): StatementViewModelFactory {
        val repository = getStatementRepository(context)
        return StatementViewModelFactory(repository)
    }
}