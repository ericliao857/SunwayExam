package com.example.sunwayexam.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.sunwayexam.data.TravelTaipeiRepository
import com.example.sunwayexam.data.toUiModel
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TravelTaipeiRepository
) : ViewModel() {
    val pagingDataFlow: Flow<PagingData<AttractionUiModel>>

    init {
        pagingDataFlow = repository.getAttractionSteam("zh-tw")
            .map { pagingDate -> pagingDate.map(Attraction::toUiModel) }
            .catch { e -> e.printStackTrace() }
            .cachedIn(viewModelScope)
    }
}