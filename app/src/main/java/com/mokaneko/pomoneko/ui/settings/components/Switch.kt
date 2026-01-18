package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.Lime
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun Switch(
    text: String
) {
    Box(
        modifier = Modifier
            .background(SemiTransparent, shape = RoundedCornerShape(10))
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = text,
                color = White,
                fontFamily = itim,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .background(Pink, shape = RoundedCornerShape(40))
                    .height(40.dp)
                    .width(100.dp),
            )
        }
    }
}