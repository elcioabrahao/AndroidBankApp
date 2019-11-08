package com.trantordev.androidbankapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClientAccountStatements (
    @SerializedName("statementList")
    @Expose
    var list: List<AccountStatement>? = null
    )

