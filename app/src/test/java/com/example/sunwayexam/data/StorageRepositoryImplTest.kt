package com.example.sunwayexam.data

import com.example.sunwayexam.data.local.LanguageSharedPref
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Locale


@ExperimentalCoroutinesApi
class StorageRepositoryImplTest{
    private val sharedPref: LanguageSharedPref = mockk()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repository: StorageRepositoryImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = StorageRepositoryImpl(sharedPref, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun saveLanguageCodeCheckSaveData() = runTest {
        val languageCode = "en"
        coEvery { sharedPref.saveLanguage(languageCode) } just Runs

        repository.saveLanguageCode(languageCode)

        coVerify { sharedPref.saveLanguage(languageCode) }
    }

    @Test
    fun getLanguageFlow_returns_flow() {
        val languageFlow = flowOf("en")
        every { sharedPref.getLanguageFlow() } returns languageFlow

        val result = repository.getLanguageFlow()

        assertEquals(languageFlow, result)
    }

    @Test
    fun getLanguageLocale_returns_local() {
        val locale = Locale("en")
        every { sharedPref.getLocale() } returns locale

        val result = repository.getLanguageLocale()

        assertEquals(locale, result)
    }
}