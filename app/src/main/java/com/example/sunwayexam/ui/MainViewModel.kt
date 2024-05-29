package com.example.sunwayexam.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunwayexam.data.StorageRepository
import com.example.sunwayexam.model.Language
import com.example.sunwayexam.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

data class MainUiState(
    val showDialog: Boolean = false,
    val language: Language = Language.ZH_TW,
    val changeLanguage: Locale? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _languageFlow: Flow<Language> = storageRepository.getLanguageFlow()
        .map { code ->
            // 預設繁體中文
            Language.entries.find { it.languageCode == code } ?: Language.ZH_TW
        }
    private val _showDialog = MutableStateFlow(false)
    private val _changeLanguage: MutableStateFlow<Locale?> = MutableStateFlow(null)

    val uiState: StateFlow<MainUiState> = combine(
        _languageFlow, _showDialog, _changeLanguage
    ) { language, showDialog, newLnaguage ->
        MainUiState(
            showDialog = showDialog,
            language = language,
            changeLanguage = newLnaguage
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = MainUiState()
    )

    /**
     * 儲存語言
     */
    fun setLanguageCode(language: Language) {
        viewModelScope.launch {
            storageRepository.saveLanguageCode(language.languageCode)
            changeNewLanguage(Locale(language.languageLocaleCode, language.countryCode))
        }
    }

    fun dialogShown() {
        _showDialog.value = false
    }

    fun showDialogMessage() {
        _showDialog.value = true
    }

    // 更改語言動作
    fun changeLanguageFinish() {
        _changeLanguage.value = null
    }

    private fun changeNewLanguage(locale: Locale) {
        _changeLanguage.value = locale
    }
}