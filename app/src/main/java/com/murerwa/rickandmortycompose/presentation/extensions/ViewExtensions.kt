package com.murerwa.rickandmortycompose.presentation.extensions

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToEnd() =
    isScrollInProgress &&
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1