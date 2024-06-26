package com.example.sunwayexam.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.sunwayexam.R
import com.example.sunwayexam.model.attraction.AttractionUiModel
import com.example.sunwayexam.utils.TestCaseUtils.testAttractionUiModel
import com.example.sunwayexam.utils.pagingLoadStateItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (AttractionUiModel) -> Unit
) {
    CompositionLocalProvider(
        androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
    ) {
        val pagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

        Scaffold(
            modifier = modifier
        ) { paddingValues ->
            HomeScreenContent(
                modifier = Modifier.padding(paddingValues),
                items = pagingItems,
                onItemClick
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<AttractionUiModel>,
    onItemClick: (AttractionUiModel) -> Unit
) {
    when (items.loadState.refresh) {
        LoadState.Loading -> {
            LoadingView(
                modifier = modifier.padding(top = 1.dp)
            )
        }

        is LoadState.Error -> {
            ErrorView(loadState = items.loadState.refresh) {
                items.retry()
            }
        }

        else -> {
            PagingView(
                modifier = modifier,
                items = items,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun PagingView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<AttractionUiModel>,
    onItemClick: (AttractionUiModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        headerView(
            loadState = items.loadState,
            retry = { items.retry() }
        )
        items(
            items.itemCount,
            key = items.itemKey { it.id },
        ) { index ->
            val attraction = items[index]
            attraction?.let {
                AttractionItem(
                    modifier = modifier,
                    attractionUiModel = it,
                    onItemClick
                )
            }
        }
        // footer
        footerView(
            loadState = items.loadState,
            retry = { items.retry() }
        )
    }
}

// Paging Header
fun LazyListScope.headerView(
    loadState: CombinedLoadStates,
    retry: () -> Unit
) {
    // header
    pagingLoadStateItem(
        loadState = loadState.prepend,
        keySuffix = "prepend",
        loading = { LoadingView() },
        error = { ErrorView(loadState = it, retry = retry) },
    )
}

// Paging Footer
fun LazyListScope.footerView(
    loadState: CombinedLoadStates,
    retry: () -> Unit
) {
    pagingLoadStateItem(
        loadState = loadState.append,
        keySuffix = "append",
        loading = { LoadingView() },
        error = { ErrorView(loadState = it, retry = retry) },
    )
}

@Composable
fun AttractionItem(
    modifier: Modifier = Modifier,
    attractionUiModel: AttractionUiModel,
    onItemClick: (AttractionUiModel) -> Unit
) {
    val rowInteractionSource = remember { MutableInteractionSource() }
    val iconInteractionSource = remember { MutableInteractionSource() }
    val iconClicked = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = rowInteractionSource,
                indication = null
            ) {
                iconClicked.value = true
                onItemClick(attractionUiModel)
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageUi(
            modifier = Modifier.weight(3.0f, fill = true),
            picUrl = attractionUiModel.images.firstOrNull()?.src
        )
        Column(
            modifier = Modifier
                .weight(7.0f, fill = true)
                .padding(start = 8.dp)
        ) {
            Text(
                text = attractionUiModel.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = attractionUiModel.introduction,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
        IconButton(
            onClick = {
                onItemClick(attractionUiModel)
            },
            interactionSource = iconInteractionSource
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.goto_content_description),
                modifier = Modifier.size(24.dp),
            )
        }

    }
    // 點 item 但 IconButton 有點擊效果
    LaunchedEffect(iconClicked.value) {
        if (iconClicked.value) {
            val press = PressInteraction.Press(Offset.Unspecified)
            iconInteractionSource.emit(press)
            iconInteractionSource.emit(PressInteraction.Release(press))
            iconClicked.value = false
        }
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp),
        color = colorResource(id = R.color.teal_200)
    )
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    loadState: LoadState? = null,
    retry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.error_load_attraction),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        TextButton(
            onClick = { retry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageUi(
    modifier: Modifier = Modifier,
    picUrl: String?
) {
    Column(
        modifier = modifier.aspectRatio(1f)
    ) {
        // 沒有圖片就給一張預設圖
        GlideImage(
            model = picUrl ?: R.drawable.ic_baseline_hide_image_24,
            contentDescription = stringResource(id = R.string.attraction_image_content_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AttractionItemPreview() {
    MaterialTheme {
        AttractionItem(
            attractionUiModel = testAttractionUiModel,
            onItemClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    MaterialTheme {
        ErrorView(
            retry = { }
        )
    }
}