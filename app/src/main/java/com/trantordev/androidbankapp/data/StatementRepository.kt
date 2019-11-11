package com.trantordev.androidbankapp.data

import com.trantordev.androidbankapp.data.database.ClientAccountInfoDao
import com.trantordev.androidbankapp.data.database.LoginDao
import com.trantordev.androidbankapp.data.network.ApiServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class StatementRepository private constructor(
    private val apiServices: ApiServices,
    private val loginDao: LoginDao,
    private val clientAccountInfoDao: ClientAccountInfoDao
) {

    suspend fun getStatements(userId: Long) = apiServices.service.getClientStatements("statements/$userId")

    suspend fun getCurrentLoggedUser() =   GlobalScope.async {
            loginDao.getLogedUser().userId
        }.await()

    suspend fun getClientInfo(userId: Long) = GlobalScope.async {
        clientAccountInfoDao.getAccountInfo(userId)
    }.await()

    suspend fun getClientInfo() = getClientInfo(getCurrentLoggedUser())

    companion object {
        @Volatile
        private var instance: StatementRepository? = null

        fun getInstance(
            apiServices: ApiServices,
            loginDao: LoginDao,
            clientAccountInfoDao: ClientAccountInfoDao
        ) =
            instance ?: synchronized(this) {
                instance ?: StatementRepository(
                    apiServices,
                    loginDao,
                    clientAccountInfoDao
                ).also { instance = it }
            }
    }
}