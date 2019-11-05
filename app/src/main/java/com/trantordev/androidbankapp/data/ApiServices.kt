package com.trantordev.androidbankapp.data

import retrofit2.Call

class ApiServices {

    var service: WebServicesAPI = RetrofitCall().getCall().create(WebServicesAPI::class.java)

    fun getAccountInfo(userId: Int): Call<ClientAccountStatements> {
        val url = "statements/$userId"
        return service.getClientStatements(url)
    }

    fun authenticate(user: String, password: String): Call<ClientAccountInfo>{
        return service.authenticate(user,password)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ApiServices? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ApiServices().also { instance = it }
            }
    }
}