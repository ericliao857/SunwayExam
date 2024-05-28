package com.example.sunwayexam.utils

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.sunwayexam.data.local.LanguageSharedPref
import java.util.Locale

/**
 * 切換語系工具
 */
class LocaleHelper(
    application: Application,
    private val languageSharedPref: LanguageSharedPref
) {

    fun initLocale(context: Context): Context? {
        return setLocale(context, languageSharedPref.getLocale())
    }

    fun setLocale(context: Context, lang: String, country: String? = null) {
        val locale = Locale(lang, country.orEmpty())
        setLocale(context, locale)
    }

    fun setLocale(context: Context, locale: Locale): Context? {
        Locale.setDefault(locale)

        val configuration: Configuration? = context.resources?.configuration
        if (configuration != null) {
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
        }

        return configuration?.let { context.createConfigurationContext(it) }
    }
}