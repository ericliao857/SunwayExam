package com.example.sunwayexam.model.attrcation


import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)