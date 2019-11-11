package com.trantordev.androidbankapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "client"
)

class ClientAccountInfo (

    @SerializedName("userId")
    var userId: Long = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("bankAccount")
    var bankAccount: String? = null,
    @SerializedName("agency")
    var agency: String? = null,
    @SerializedName("balance")
    var balance: Double? = 0.0

)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var clientId: Long = 0
}
