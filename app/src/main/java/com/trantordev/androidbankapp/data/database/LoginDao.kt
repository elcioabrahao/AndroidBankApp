package com.trantordev.androidbankapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trantordev.androidbankapp.data.model.Login

@Dao
interface LoginDao {
    @Query("SELECT * FROM login")
    fun getUserInfo(): LiveData<List<Login>>

    @Query("SELECT EXISTS(SELECT 1 FROM login WHERE user = :user and password = :password LIMIT 1)")
    fun isCached(user: String, password: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(login: Login): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Login>)

    @Delete
    suspend fun deleteUserInfo(login: Login)

    @Query("delete from login")
    suspend fun deleteAll()
}