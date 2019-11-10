package com.trantordev.androidbankapp.data.model

import com.google.gson.annotations.SerializedName

data class ClientAccountInfoResponse (
    @SerializedName("userAccount")
    var clientAccountInfo: ClientAccountInfo,
    @SerializedName("error")
    var error: Error
)