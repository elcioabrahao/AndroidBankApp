package com.trantordev.androidbankapp.data

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface WebServicesAPI {

    @POST("login")
    @FormUrlEncoded
    fun authenticate(
        @Field("user") login: String,
        @Field("password") password: String
    ): Call<ClientAccountInfo>

    @GET
    fun getClientStatements(@Url url:String):Call<ClientAccountStatements>

}