package com.example.sunwayexam.data.api

import com.example.sunwayexam.model.ApiBean
import com.example.sunwayexam.model.attrcation.Attraction
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("{lang}/Attractions/All")
    @Headers("Accept: application/json")
    suspend fun getAttractions(@Path("lang") lang: String): ApiBean<List<Attraction>>


    companion object {
        const val DOMAIN = "https://www.travel.taipei/open-api/"
    }
}