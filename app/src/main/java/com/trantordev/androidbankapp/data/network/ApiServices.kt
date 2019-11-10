package com.trantordev.androidbankapp.data.network

import androidx.lifecycle.LiveData
import com.trantordev.androidbankapp.data.model.AccountStatement
import com.trantordev.androidbankapp.data.model.ClientAccountInfo
import com.trantordev.androidbankapp.data.model.ClientAccountStatements
import retrofit2.Call

class ApiServices {

    var service: WebServicesAPI = RetrofitCall()
        .getCall().create(WebServicesAPI::class.java)

     companion object {
        @Volatile private var instance: ApiServices? = null

        fun getInstance() =
            instance
                ?: synchronized(this) {
                instance
                    ?: ApiServices().also { instance = it }
            }
    }
}