package com.trantordev.androidbankapp.data.model

import androidx.room.*

@Entity(
    tableName = "login"
)
data class Login(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var userId: Long,
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "password")
    val password: String

)