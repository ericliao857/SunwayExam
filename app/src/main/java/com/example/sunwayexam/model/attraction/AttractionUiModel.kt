package com.example.sunwayexam.model.attraction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * 旅遊景點UiModel (只提供需要呈現的資料)
 */
@Parcelize
data class AttractionUiModel(
    val id: Int,
    val name: String,
    val introduction: String,
    val address: String,
    val images: List<Image>,
    val modified: String,
    val officialSite: String
): Parcelable
