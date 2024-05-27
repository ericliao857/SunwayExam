package com.example.sunwayexam.data

import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel

/**
 * Data class 轉換的擴展
 */
fun Attraction.toUiModel() = AttractionUiModel(
    id = id,
    name = name,
    introduction = introduction,
    address = address,
    images = images,
    modified = modified,
    // Android 不建議使用http
    officialSite = officialSite.replace("http://", "https://")
)
fun List<Attraction>.toUiModel() = map(Attraction::toUiModel)