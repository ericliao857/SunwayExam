package com.example.sunwayexam.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

const val ARG_ATTRACTION = "attraction" // 旅遊景點

@AndroidEntryPoint
class DetailFragment : Fragment() {
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
                    DetailScreen(
                        openWebView = { url, title ->
                            goToWebView(url, title)
                        }
                    )
                }
            }
        }
    }

    private fun goToWebView(url: String, title: String) {
        val direction = DetailFragmentDirections
            .actionNavDetailToNavWebView(url = url, title = title)
        findNavController().navigate(direction)
    }
}