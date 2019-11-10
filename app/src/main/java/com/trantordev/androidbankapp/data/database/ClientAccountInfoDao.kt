package com.trantordev.androidbankapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trantordev.androidbankapp.data.model.ClientAccountInfo

@Dao
interface ClientAccountInfoDao {

    @Query("SELECT * FROM client")
    fun getAllClientsAccountInfo(): LiveData<List<ClientAccountInfo>>

    @Query("SELECT * FROM client WHERE userId = :userId LIMIT 1")
    suspend fun getAccountInfo(userId: Long): ClientAccountInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClientAccountInfo(clientAccountInfo: ClientAccountInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clients: List<ClientAccountInfo>)

    @Delete
    suspend fun deleteClientAccountInfo(clientAccountInfo: ClientAccountInfo)

    @Query("delete from client")
    suspend fun deleteAll()
}