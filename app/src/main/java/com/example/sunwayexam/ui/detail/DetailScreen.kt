package com.example.sunwayexam.ui.detail

import android.R.color
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sunwayexam.R
import com.example.sunwayexam.model.attraction.AttractionUiModel
import com.example.sunwayexam.utils.TestCaseUtils


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    openWebView: (url: String) -> Unit
) {
    CompositionLocalProvider(
        androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
    ) {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold(
            modifier = modifier
        ) { paddingValues ->
            DetailScreenContent(
                modifier = Modifier.padding(paddingValues),
                attraction = uiState.value.attraction,
                openWebView
            )
        }
    }
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    attraction: AttractionUiModel?,
    openWebView: (url: String) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        attraction?.apply {
            AttractionImage(picUrl = images.firstOrNull()?.src)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = introduction,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LabelAndContentView(
                    label = stringResource(id = R.string.address),
                    content = address
                )
                Spacer(modifier = Modifier.height(8.dp))
                LabelAndContentView(
                    label = stringResource(id = R.string.last_update_time),
                    content = modified
                )
                Spacer(modifier = Modifier.height(8.dp))
                URLTextView(webUrl = url)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AttractionImage(
    modifier: Modifier = Modifier,
    picUrl: Any?
) {
    // 沒有圖片就給一張預設圖
    GlideImage(
        model = picUrl ?: R.drawable.ic_baseline_hide_image_24,
        contentDescription = stringResource(id = R.string.attraction_image_content_description),
        modifier = modifier
            .aspectRatio(4f / 3f)
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LabelAndContentView(
    label: String,
    content: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 14.sp
        )

        content?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = it,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun URLTextView(
    webUrl: String
) {
    val tag = "URL"
    val annotatedString = buildAnnotatedString {
        pushStringAnnotation(tag = tag, annotation = webUrl)
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(webUrl)
        }
        pop()
    }
    ClickableText(
        text = annotatedString,
        style = TextStyle.Default.copy(fontSize = 14.sp),
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                Log.d("policy URL", it.item)
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DetailScreenContentPreview() {
    MaterialTheme {
        DetailScreenContent(
            attraction = TestCaseUtils.testAttractionUiModel,
            openWebView = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AttractionImagePreview() {
    MaterialTheme {
        AttractionImage(
            picUrl = R.drawable.ic_baseline_hide_image_24
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabelAndContentViewPreview() {
    MaterialTheme {
        LabelAndContentView(
            label = "地址",
            content = TestCaseUtils.testAttractionUiModel.address
        )
    }
}

@Preview(showBackground = true)
@Composable
fun URLTextViewPreview() {
    MaterialTheme {
        URLTextView(
            webUrl = TestCaseUtils.testAttractionUiModel.url
        )
    }
}