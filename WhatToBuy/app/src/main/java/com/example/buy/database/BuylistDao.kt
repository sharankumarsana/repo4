package com.example.buy.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.buy.model.Model

@Dao
interface BuylistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addnote(note:Model)

    @Delete
    suspend fun deleteNote(note: Model)

    @Update
    suspend fun updateNote(note: Model)

    @Query("select * from NotesTable")
    fun listNotes():LiveData<List<Model>>
}