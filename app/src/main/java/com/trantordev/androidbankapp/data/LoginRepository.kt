package com.trantordev.androidbankapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository private constructor(
    private val loginDao: LoginDao,
    private val clientAccountInfoDao: ClientAccountInfoDao,
    private val apiServices: ApiServices
) {

    suspend fun createLogin(user: String, password: String) {
        val login = Login(user, password)
        loginDao.insertUserInfo(login)
    }

    suspend fun removeLogin(login: Login) {
        loginDao.deleteUserInfo(login)
    }

    fun isCached(login: Login) =
        loginDao.isCached(login.user, login.password)

    fun getUserInfo(login: Login): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        if (isCached(login)) {
            loginResponse.value = "OK"
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
                        loginResponse.value = "OK"
                        clientAccountInfoDao.insertClientAccountInfo(response.body()!!)
                    } else {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }
            })

            loginDao.insertUserInfo(login)
            return loginResponse
        }

    }


    companion object {

        // For Singleton instantiation
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