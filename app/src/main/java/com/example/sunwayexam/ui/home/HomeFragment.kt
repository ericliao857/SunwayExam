package com.example.sunwayexam.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import com.example.sunwayexam.databinding.FragmentHomeBinding
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.model.attraction.AttractionUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    HomeScreen(
                        onItemClick = {
                            goToDetail(it)
                        }
                    )
                }
            }
        }
    }

    /**
     * 前往詳細說明頁面
     */
    private fun goToDetail(attraction: AttractionUiModel) {
        val direction = HomeFragmentDirections
            .actionNavHomeToDetail(attraction = attraction, title = attraction.name)
        findNavController().navigate(direction)
    }

}