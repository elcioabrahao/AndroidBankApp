package com.trantordev.androidbankapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trantordev.androidbankapp.data.database.ClientAccountInfoDao
import com.trantordev.androidbankapp.data.database.LoginDao
import com.trantordev.androidbankapp.data.model.ClientAccountInfo
import com.trantordev.androidbankapp.data.model.ClientAccountInfoResponse
import com.trantordev.androidbankapp.data.model.Login
import com.trantordev.androidbankapp.data.network.ApiServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository private constructor(
    private val loginDao: LoginDao,
    private val clientAccountInfoDao: ClientAccountInfoDao,
    private val apiServices: ApiServices
) {

    suspend fun isCached(login: Login): Boolean {
        return GlobalScope.async {
            loginDao.isCached(login.user, login.password)
        }.await()
    }

    suspend fun getUserInfo(login: Login): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        if (isCached(login) == true) {
            loginResponse.value = "LOGGED DATABASE"
            return loginResponse
        } else {

            val response: ClientAccountInfoResponse
            try{

            response = authenticate(login)
                if(response.clientAccountInfo != null){
                    GlobalScope.async {
                        saveClientrInfo(response.clientAccountInfo)
                        login.userId=response.clientAccountInfo.userId
                        saveUserInfo(login)
                    }
                    loginResponse.value = "OK"
                }else{
                    if(response.error != null){
                        loginResponse.value = response.error.message
                    }
                }
            } catch (e: Exception) {
                loginResponse.value = "Erro na autenticação de usuário"
            }

            return loginResponse
        }

    }

    suspend fun authenticate(login: Login)
            =  apiServices.service.authenticate(login.user, login.password)

    suspend fun saveUserInfo(login: Login) {
        GlobalScope.async {
            loginDao.deleteAll()
            loginDao.insertUserInfo(login)
        }.await()

    }

    suspend fun saveClientrInfo(clientAccountInfo: ClientAccountInfo) {
        GlobalScope.async {
            clientAccountInfoDao.deleteAll()
            clientAccountInfoDao.insertClientAccountInfo(clientAccountInfo)
            val clientAccountInfo = clientAccountInfoDao.getAccountInfo(1)
            Log.d("STATEMENTS","CLIENTE GRAVADO: "+clientAccountInfo.toString())
        }.await()
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(
            loginDao: LoginDao,
            clientAccountInfoDao: ClientAccountInfoDao,
            apiServices: ApiServices
        ) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(
                    loginDao,
                    clientAccountInfoDao,
                    apiServices
                ).also { instance = it }
            }
    }
}