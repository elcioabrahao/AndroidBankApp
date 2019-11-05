package com.trantordev.androidbankapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClientAccountStatements {

    // Resposta da chamada
    @SerializedName("statementList")
    @Expose
    var list: List<Statament>? = null
}