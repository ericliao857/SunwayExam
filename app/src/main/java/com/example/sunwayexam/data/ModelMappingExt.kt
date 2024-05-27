package com.example.sunwayexam.data

import com.example.sunwayexam.model.attrcation.Attraction
import com.example.sunwayexam.model.attrcation.AttractionUiModel

/**
 * Data class 轉換的擴展
 */
fun Attraction.toUiModel() = AttractionUiModel(
    id = id,
    name = name,
    introduction = introduction,
    address = address,
    images = images,
    modified = modified
)
fun List<Attraction>.toUiModel() = map(Attraction::toUiModel)