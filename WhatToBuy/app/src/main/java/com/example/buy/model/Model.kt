package com.example.buy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BuylistTable")
data class Model(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var groceries:String,
    var medicines:String,
var others:String

)
