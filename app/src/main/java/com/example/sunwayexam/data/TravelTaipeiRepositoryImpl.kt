package com.example.sunwayexam.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sunwayexam.data.api.ApiService
import com.example.sunwayexam.data.paging.AttractionPagingSource
import com.example.sunwayexam.model.attrcation.Attraction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TravelTaipeiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TravelTaipeiRepository {
    override fun getAttractionSteam(lang: String): Flow<PagingData<Attraction>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AttractionPagingSource(apiService, lang) }
        ).flow
    }
    companion object {
        const val PAGE_SIZE = 30    // 每次回應30筆(Api Swagger寫的)
    }

}