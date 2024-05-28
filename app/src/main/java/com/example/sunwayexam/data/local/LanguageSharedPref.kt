package com.example.sunwayexam.data.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * 儲存語言設定
 */
class LanguageSharedPref(context: Context) {
    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun saveLanguage(name: String) {
        pref.let {
            pref.edit().putString(LANGUAGE, name).apply()
        }
    }

    fun getLanguage(): String? {
        return pref.getString(LANGUAGE, "zh-tw")
    }

    fun getLanguageFlow(): Flow<String> {
        return pref.getStringFlow(LANGUAGE, "zh-tw")
    }

    /**
     * 取得SharedPref 更新值
     */
    private fun SharedPreferences.getStringFlow(key: String, defaultValue: String): Flow<String> {
        return callbackFlow {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, k ->
                if (key == k) {
                    trySend(getString(key, defaultValue) ?: defaultValue).isSuccess
                }
            }

            registerOnSharedPreferenceChangeListener(listener)
            trySend(getString(key, defaultValue) ?: defaultValue).isSuccess
            awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
        }
    }

    companion object {
        const val PREF_NAME = "storage"
        const val LANGUAGE = "Language"
    }
}