package com.trantordev.androidbankapp.data.model

import com.google.gson.annotations.SerializedName

class Error (
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String
)