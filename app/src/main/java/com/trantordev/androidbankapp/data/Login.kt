package com.trantordev.androidbankapp.data

import androidx.room.*

@Entity(
    tableName = "login"
)
data class Login(
    @ColumnInfo(name = "user") val user: String,

    @ColumnInfo(name = "password") val password: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userId: Long = 0
}