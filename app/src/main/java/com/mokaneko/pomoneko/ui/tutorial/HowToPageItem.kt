package com.mokaneko.pomoneko.ui.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun HowToPageItem(
    page: HowToPageData,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(page.imageRes),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentScale = ContentScale.Fit
    )
}
