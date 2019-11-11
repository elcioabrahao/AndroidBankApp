package com.trantordev.androidbankapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.*

import com.trantordev.androidbankapp.data.StatementRepository
import com.trantordev.androidbankapp.ui.StatementListener
import kotlinx.coroutines.Dispatchers

class StatementViewModel internal constructor(
    private val statementRepository: StatementRepository
): ViewModel() {

    var statementListener: StatementListener? = null
    var userId: Long = 1


    val statements = liveData(Dispatchers.IO) {
        val retrivedStatements = statementRepository.getStatements(userId)
        emit(retrivedStatements)
    }

    val clientInfo = liveData(Dispatchers.IO){
        val retrivedClientAccountInfo = statementRepository.getClientInfo()
        emit(retrivedClientAccountInfo)
    }

    fun onLogoutIconClick(view: View){
       statementListener!!.onClose()
    }


}