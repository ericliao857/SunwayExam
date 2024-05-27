package com.example.sunwayexam.model.attrcation


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("ext")
    val ext: String,
    @SerializedName("src")
    val src: String,
    @SerializedName("subject")
    val subject: String
)