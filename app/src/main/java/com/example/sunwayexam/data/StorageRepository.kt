package com.example.sunwayexam.data

import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun saveLanguageCode(languageCode: String)
    fun getLanguageFlow(): Flow<String>
}