package com.example.sunwayexam

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import com.example.sunwayexam.data.local.LanguageSharedPref
import com.example.sunwayexam.model.Language
import com.example.sunwayexam.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ExamApplication: Application() {
    @Inject
    lateinit var languageSharedPref: LanguageSharedPref
    override fun onCreate() {
        super.onCreate()
        val locale = languageSharedPref.getLocale()
        LocaleHelper(this, languageSharedPref).setLocale(this, locale)
    }
}