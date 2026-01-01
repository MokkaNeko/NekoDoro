package com.mokaneko.pomoneko.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.R

val itim = FontFamily(
    Font(R.font.itim, FontWeight.Normal)
)

val poppins = FontFamily (
    listOf(
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = White
    ),
    bodyLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = White
    ),
    labelSmall = TextStyle(
        color = White,
        fontFamily = itim,
        fontSize = 20.sp
    ),
    labelLarge = TextStyle(
        color = White,
        fontFamily = itim,
        fontSize = 32.sp
    )
)