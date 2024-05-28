package com.example.sunwayexam.data

import com.example.sunwayexam.data.local.LanguageSharedPref
import com.example.sunwayexam.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val sharedPref: LanguageSharedPref,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : StorageRepository {

    override suspend fun saveLanguageCode(languageCode: String) {
        withContext(dispatcher) {
            sharedPref.saveLanguage(languageCode)
        }
    }

    override fun getLanguageFlow(): Flow<String> {
        return sharedPref.getLanguageFlow()
    }

    override fun getLanguageLocale(): Locale {
        return sharedPref.getLocale()
    }

}