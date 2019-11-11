package com.trantordev.androidbankapp.data.model

import com.google.gson.annotations.SerializedName

class AccountStatement {
    @SerializedName("title")
    var title: String? = null
    @SerializedName("desc")
    var desc: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("value")
    var value: Double? = null

    fun getFormatedDate() = PresentationUtils.getFormatedDate(date)
    fun getFormatedCurrency() = PresentationUtils.getFormatedCurrency(value)
}
