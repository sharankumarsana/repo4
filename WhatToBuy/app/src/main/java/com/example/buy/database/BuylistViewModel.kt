package com.example.buy.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.buy.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BuylistViewModel(application: Application):AndroidViewModel(application) {
        val readAll:LiveData<List<Model>>
        private val notesrepo:BuylistRepository

    init {
            val notesDao=BuylistDatabase.getDatabase(application).notesDao()
            notesrepo= BuylistRepository(notesDao)
            readAll=notesrepo.readAllData
    }

    fun addNote(note:Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.addnote(note)
        }
    }

    fun viewNote(note: Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.updateNOte(note)
        }
    }

    fun deleteNote(note: Model){
        viewModelScope.launch(Dispatchers.IO){
            notesrepo.deleteNote(note)
        }
    }


}