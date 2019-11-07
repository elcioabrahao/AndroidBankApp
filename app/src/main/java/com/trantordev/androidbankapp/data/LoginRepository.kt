package com.trantordev.androidbankapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trantordev.androidbankapp.data.database.ClientAccountInfoDao
import com.trantordev.androidbankapp.data.database.LoginDao
import com.trantordev.androidbankapp.data.model.ClientAccountInfo
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
            loginDao.isCached(login.user,login.password)
        }.await()
    }

    suspend fun getUserInfo(login: Login): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        if (isCached(login) == true) {
            loginResponse.value = "LOGGED DATABASE"
            return loginResponse
        } else {

            apiServices.authenticate(login.user, login.password).enqueue(object :
                Callback<ClientAccountInfo> {
                override fun onFailure(call: Call<ClientAccountInfo>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ClientAccountInfo>,
                    response: Response<ClientAccountInfo>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = "LOGGED API"
                        GlobalScope.async {
                            saveClientrInfo(response.body()!!)
                            saveUserInfo(login)
                        }
                    } else {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }
            })
            return loginResponse
        }

    }

    suspend fun saveUserInfo(login: Login){
         GlobalScope.async {
             loginDao.deleteAll()
            loginDao.insertUserInfo(login)
        }.await()

    }

    suspend fun saveClientrInfo(clientAccountInfo: ClientAccountInfo){
        GlobalScope.async {
            clientAccountInfoDao.deleteAll()
            clientAccountInfoDao.insertClientAccountInfo(clientAccountInfo)
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