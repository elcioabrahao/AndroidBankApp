package com.trantordev.androidbankapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trantordev.androidbankapp.data.model.AccountStatement
import com.trantordev.androidbankapp.data.model.ClientAccountInfo
import com.trantordev.androidbankapp.data.model.ClientAccountStatements
import com.trantordev.androidbankapp.data.network.ApiServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementRepository private constructor(
    private val apiServices: ApiServices
) {

//    fun getStatements(userId: Int): LiveData<List<AccountStatement>> {
//
//
//        apiServices.getAccountInfo(userId).enqueue(object :
//            Callback<ClientAccountStatements> {
//            override fun onFailure(call: Call<ClientAccountStatements>, t: Throwable) {
//                loginResponse.value = null
//            }
//
//            override fun onResponse(
//                call: Call<ClientAccountStatements>,
//                response: Response<ClientAccountStatements>
//            ) {
//                if (response.isSuccessful) {
//                    loginResponse.value = response.body()!!.list
//                } else {
//                    Log.d("STATEMENTS", "ERRO API")
//                    loginResponse.value = null
//                }
//            }
//        })
//
//
//        Log.d("STATEMENTS", "Tamanho da lista aqui: "+loginResponse.value!!.size)
//        return loginResponse
//    }

suspend fun getStatements(userId: Int) = apiServices.service.getClientStatements("statements/$userId")

    companion object {
        @Volatile
        private var instance: StatementRepository? = null

        fun getInstance(
            apiServices: ApiServices
        ) =
            instance ?: synchronized(this) {
                instance ?: StatementRepository(
                    apiServices
                ).also { instance = it }
            }
    }
}