package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun AdditionalComponents(
    modifier: Modifier = Modifier,
    text : String,
    fontSize: Int = 35,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick },
        modifier = modifier
            .height(90.dp)
            .width(90.dp),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = SemiTransparent,
            contentColor = White
        )
    ) {
        Text(
            text = text,
            color = White,
            fontFamily = itim,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold
        )
    }
}