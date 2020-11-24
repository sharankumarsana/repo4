package com.example.buy.database

import androidx.lifecycle.LiveData
import com.example.buy.model.Model

class BuylistRepository(val buylistDao: BuylistDao) {
    val readAllData:LiveData<List<Model>> = buylistDao.listNotes()

    suspend fun addlist(note:Model){
       buylistDao.addlist(note)
}
    suspend fun updatelist(note: Model){
        buylistDao.updatelist(note)
    }

    suspend fun deletelist(note: Model){
        buylistDao.deletelist(note)
    }

}