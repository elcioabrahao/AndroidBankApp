package com.trantordev.androidbankapp.data.network

import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitCall {

    private var BASE_URL: String = "https://bank-app-test.herokuapp.com/api/"
    private var retrofit: Retrofit? = null

    fun getCall(): Retrofit {

        if (retrofit == null) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = Builder().addInterceptor(interceptor).build()


            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!
    }

}
