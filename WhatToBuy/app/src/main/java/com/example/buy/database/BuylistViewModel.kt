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
        private val buylistRepository:BuylistRepository

    init {
            val buylistDao=BuylistDatabase.getDatabase(application).buylistDao()
            buylistRepository= BuylistRepository(buylistDao)
            readAll=buylistRepository.readAllData
    }

    fun addlist(model:Model){
        viewModelScope.launch(Dispatchers.IO){
            buylistRepository.addlist(model)
        }
    }

    fun viewlist(model: Model){
        viewModelScope.launch(Dispatchers.IO){
            buylistRepository.updatelist(model)
        }
    }

    fun deletelist(model: Model){
        viewModelScope.launch(Dispatchers.IO){
            buylistRepository.deletelist(model)
        }
    }


}