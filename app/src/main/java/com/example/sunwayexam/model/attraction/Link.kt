package com.example.sunwayexam.model.attraction


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("src")
    val src: String,
    @SerializedName("subject")
    val subject: String
)