package com.trantordev.androidbankapp.viewmodel

import androidx.lifecycle.*

import com.trantordev.androidbankapp.data.StatementRepository
import kotlinx.coroutines.Dispatchers

class StatementViewModel internal constructor(private val statementRepository: StatementRepository
): ViewModel() {
    var userId: Int = 1
    val statements = liveData(Dispatchers.IO) {
        val retrivedStatements = statementRepository.getStatements(userId)
        emit(retrivedStatements)
    }

}