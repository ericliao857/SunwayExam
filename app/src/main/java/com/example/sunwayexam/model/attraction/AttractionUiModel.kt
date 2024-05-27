package com.example.sunwayexam.model.attraction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * 旅遊景點UiModel (只提供需要呈現的資料)
 */
@Parcelize
data class AttractionUiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("url")
    val url: String
): Parcelable
