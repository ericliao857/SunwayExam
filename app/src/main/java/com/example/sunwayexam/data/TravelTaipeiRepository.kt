package com.example.sunwayexam.data

import androidx.paging.PagingData
import com.example.sunwayexam.model.attrcation.Attraction
import kotlinx.coroutines.flow.Flow

interface TravelTaipeiRepository {
    fun getAttractionSteam(lang: String): Flow<PagingData<Attraction>>
}