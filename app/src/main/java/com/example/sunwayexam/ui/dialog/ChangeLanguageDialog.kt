package com.example.sunwayexam.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sunwayexam.R
import com.example.sunwayexam.model.Language

@Composable
fun ChangeLanguageDialog(
    currentLang: Language? = null,
    onDismissRequest: (() -> Unit)? = null,
    onItemClick: (language: Language) -> Unit
) {
    val languages = Language.entries.toTypedArray()
    Dialog(
        onDismissRequest = {
            onDismissRequest?.let { it() }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        SelectCropContent(
            languages = languages,
            currentLang = currentLang,
            onItemClick = onItemClick,
            onDismissRequest = onDismissRequest
        )
    }
}

@Composable
fun SelectCropContent(
    languages: Array<Language>,
    currentLang: Language? = null,
    onItemClick: (language: Language) -> Unit,
    onDismissRequest: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.change_language),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            LazyColumn {
                items(languages) { language ->
                    ItemView(
                        language = language,
                        isSelected = currentLang?.let { language == it } ?: false,
                        onItemClick = onItemClick
                    )
                }
            }
            BottomButton(onDismissRequest)
        }
    }
}

@Composable
fun ItemView(
    language: Language,
    isSelected: Boolean = false,
    onItemClick: (language: Language) -> Unit
) {
    TextButton(
        onClick = { onItemClick(language) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (isSelected) {
                androidx.compose.material.MaterialTheme.colors.primary
            } else {
                Color.Transparent
            }
        )
    ) {
        Text(
            text = language.languageName,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = if (isSelected) {
                Color.White
            } else {
                Color.Unspecified
            }
        )
    }
}

@Composable
fun BottomButton(
    onDismissRequest: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End,
    ) {
        onDismissRequest?.let {
            TextButton(
                onClick = { onDismissRequest() },
            ) {
                Text("Dismiss")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SelectCropDialogPreview() {
    MaterialTheme {
        SelectCropContent(
            languages = Language.entries.toTypedArray(),
            onDismissRequest = { },
            onItemClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemViewPreview() {
    MaterialTheme {
        ItemView(
            language = Language.ZH_TW,
            isSelected = false,
            onItemClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemViewIsSelectedPreview() {
    MaterialTheme {
        ItemView(
            language = Language.ZH_TW,
            isSelected = true,
            onItemClick = { },
        )
    }
}