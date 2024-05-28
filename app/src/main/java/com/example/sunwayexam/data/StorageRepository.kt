package com.example.sunwayexam.data

import kotlinx.coroutines.flow.Flow
import java.util.Locale

interface StorageRepository {
    suspend fun saveLanguageCode(languageCode: String)
    fun getLanguageFlow(): Flow<String>
    fun getLanguageLocale(): Locale
}