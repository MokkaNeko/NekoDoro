package com.mokaneko.pomoneko.ui.tutorial.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun HowToActionButton(
    isLastPage: Boolean,
    onSkip: () -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = {
            if (isLastPage) onDone() else onSkip()
        },
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = White
        )
    ) {
        Text(
            text = if (isLastPage) "Done" else "Skip",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
