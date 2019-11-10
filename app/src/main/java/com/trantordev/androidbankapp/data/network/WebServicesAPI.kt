package com.trantordev.androidbankapp.data.network

import com.trantordev.androidbankapp.data.model.ClientAccountInfo
import com.trantordev.androidbankapp.data.model.ClientAccountInfoResponse
import com.trantordev.androidbankapp.data.model.ClientAccountStatements
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface WebServicesAPI {

    @POST("login")
    @FormUrlEncoded
    suspend fun authenticate(
        @Field("user") login: String,
        @Field("password") password: String
    ): ClientAccountInfoResponse

    @GET
    suspend fun getClientStatements(@Url url:String):ClientAccountStatements

}