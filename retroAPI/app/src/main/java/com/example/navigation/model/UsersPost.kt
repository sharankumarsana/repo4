package com.example.navigation.model

import com.google.gson.annotations.SerializedName

data class UsersPost (
     @SerializedName("name")
     val name: String,
     @SerializedName("email")
     val email: String,
@SerializedName("gender")
val gender: String,
@SerializedName("status")
val status:String
)
