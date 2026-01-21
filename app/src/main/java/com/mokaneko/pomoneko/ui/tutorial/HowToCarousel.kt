package com.mokaneko.pomoneko.ui.tutorial

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.DarkGreen
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.tutorial.components.HowToActionButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HowToCarousel(
    pages: List<HowToPageData>,
    modifier: Modifier = Modifier,
    onFinish : () -> Unit
) {
    val pagerState = rememberPagerState { pages.size }
    val lastIndex = pages.lastIndex
    val scope = rememberCoroutineScope()

    val backgroundColor by animateColorAsState(
        targetValue = if (pagerState.currentPage == pages.lastIndex)
            Green else DarkGreen,
        label = "HowToBackground"
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pointerInput(pagerState.currentPage) {
                detectTapGestures {
                    if (pagerState.currentPage < lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    } else {
                        onFinish()
                    }
                }
            }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HowToPageItem(
                    page = pages[pageIndex]
                )
            }
        }

        PagerIndicator(
            pageCount = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )

        HowToActionButton(
            isLastPage = pagerState.currentPage == lastIndex,
            onSkip = onFinish,
            onDone = onFinish,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp, top = 32.dp)
        )
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .size(
                        width = if (index == currentPage) 18.dp else 8.dp,
                        height = 8.dp
                    )
                    .clip(CircleShape)
                    .background(
                        if (index == currentPage) Color.White
                        else Color.White.copy(alpha = 0.4f)
                    )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7)
@Composable
fun HowToCarouselPreview() {
    HowToCarousel(
        pages = howToPages,
        onFinish = {}
    )
}

