package com.trantordev.androidbankapp.viewmodel

import android.util.Log
import androidx.lifecycle.*

import com.trantordev.androidbankapp.data.StatementRepository
import kotlinx.coroutines.Dispatchers

class StatementViewModel internal constructor(
    private val statementRepository: StatementRepository
): ViewModel() {
    var userId: Long = 1
    val statements = liveData(Dispatchers.IO) {
        val retrivedStatements = statementRepository.getStatements(userId)
        emit(retrivedStatements)
    }

    val clientInfo = liveData(Dispatchers.IO){
        val retrivedClientAccountInfo = statementRepository.getClientInfo()
        Log.d("STATEMENTS","Viewmodel-->"+retrivedClientAccountInfo.toString())
        emit(retrivedClientAccountInfo)
    }




}