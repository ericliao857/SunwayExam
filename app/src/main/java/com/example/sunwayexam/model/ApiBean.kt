package com.example.sunwayexam.model

import com.google.gson.annotations.SerializedName

/**
 * Api 的外層包裝
 */
data class ApiBean<T>(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: T
)
