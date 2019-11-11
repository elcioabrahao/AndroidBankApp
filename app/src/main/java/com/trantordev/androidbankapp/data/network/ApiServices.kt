package com.trantordev.androidbankapp.data.network

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