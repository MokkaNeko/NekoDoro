package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.common.icons.MinusIcon
import com.mokaneko.pomoneko.ui.common.icons.PlusIcon
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.Transparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun DurationComponents(
    modifier: Modifier = Modifier,
    duration: Int,
    onPlusClick: () -> Unit = {},
    onMinusClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .height(90.dp)
            .width(90.dp)
            .background(
                color = SemiTransparent,
                shape = RoundedCornerShape(10)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = onPlusClick,
                modifier = Modifier.size(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Transparent,
                    contentColor = White
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                PlusIcon(modifier = Modifier.fillMaxSize(0.5f))
            }
            Text(
                text = duration.toString(),
                color = White,
                fontFamily = itim,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = onMinusClick,
                modifier = Modifier.size(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Transparent,
                    contentColor = White
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                MinusIcon(modifier = Modifier.fillMaxSize(0.5f))
            }
        }
    }

}

@Composable
@Preview
fun DurationPreview() {
    DurationComponents(duration = 50)
}