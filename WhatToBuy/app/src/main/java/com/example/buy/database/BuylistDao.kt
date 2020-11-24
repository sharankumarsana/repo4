package com.example.buy.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.buy.model.Model

@Dao
interface BuylistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addlist(note:Model)

    @Delete
    suspend fun deletelist(note: Model)

    @Update
    suspend fun updatelist(note: Model)

    @Query("select * from BuylistTable")
    fun listNotes():LiveData<List<Model>>
}