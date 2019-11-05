package com.trantordev.androidbankapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClientAccountInfoDao {

    @Query("SELECT * FROM client")
    fun getAllClientsAccountInfo(): LiveData<List<ClientAccountInfo>>

    @Query("SELECT * FROM client WHERE userId = :user LIMIT 1")
    fun getAccountInfo(user: String): LiveData<ClientAccountInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClientAccountInfo(clientAccountInfo: ClientAccountInfo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clients: List<ClientAccountInfo>)

    @Delete
    suspend fun deleteClientAccountInfo(clientAccountInfo: ClientAccountInfo)
}