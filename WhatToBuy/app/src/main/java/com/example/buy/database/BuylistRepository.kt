package com.example.buy.database

import androidx.lifecycle.LiveData
import com.example.buy.model.Model

class BuylistRepository(val buylistDao: BuylistDao) {
    val readAllData:LiveData<List<Model>> = buylistDao.listNotes()

    suspend fun addnote(note:Model){
       buylistDao.addnote(note)
}
    suspend fun updateNOte(note: Model){
        buylistDao.updateNote(note)
    }

    suspend fun deleteNote(note: Model){
        buylistDao.deleteNote(note)
    }

}