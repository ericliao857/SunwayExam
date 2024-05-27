package com.example.sunwayexam.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel
import com.example.sunwayexam.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class DetailUiState(
    val attraction: AttractionUiModel? = null
)
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val uiState: StateFlow<DetailUiState> =
        savedStateHandle.getStateFlow<AttractionUiModel?>(ARG_ATTRACTION, null)
            .map {
                DetailUiState(
                    attraction = it
                )
            }.stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = DetailUiState()
            )
}