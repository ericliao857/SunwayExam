package com.example.sunwayexam.ui.home

import androidx.lifecycle.ViewModel
import com.example.sunwayexam.data.api.TravelTaipeiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TravelTaipeiRepository
) : ViewModel() {

}