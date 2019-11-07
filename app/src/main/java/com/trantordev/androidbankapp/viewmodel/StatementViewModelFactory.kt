package com.trantordev.androidbankapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trantordev.androidbankapp.data.StatementRepository

class StatementViewModelFactory(private val repository: StatementRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatementViewModel(repository) as T
    }
}