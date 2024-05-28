package com.example.sunwayexam.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.example.sunwayexam.model.Language

/**
 * 更改語言 Dialog
 */
class LanguageDialogFragment(
    val language: Language,
    private val changeLanguage: (Language) -> Unit
) : DialogFragment() {
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
                    ChangeLanguageDialog(
                        currentLang = language,
                        onDismissRequest = {
                            dismiss()
                        },
                        onItemClick = {
                            changeLanguage(it)
                            dismiss()
                        }
                    )
                }
            }
        }
    }

    companion object {
        val TAG = LanguageDialogFragment::class.simpleName

    }
}