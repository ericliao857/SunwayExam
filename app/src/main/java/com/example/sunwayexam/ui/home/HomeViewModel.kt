package com.example.sunwayexam.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.sunwayexam.data.StorageRepository
import com.example.sunwayexam.data.TravelTaipeiRepository
import com.example.sunwayexam.data.toUiModel
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storageRepository: StorageRepository,
    private val repository: TravelTaipeiRepository
) : ViewModel() {
    private val _languageFlow: Flow<String> = storageRepository.getLanguageFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingDataFlow: Flow<PagingData<AttractionUiModel>> = _languageFlow
        .flatMapLatest { languageCode ->
            loadAttraction(languageCode)
        }.cachedIn(viewModelScope)

    private fun loadAttraction(languageCode: String): Flow<PagingData<AttractionUiModel>> =
        repository.getAttractionSteam(languageCode)
            .map { pagingDate -> pagingDate.map(Attraction::toUiModel) }
}