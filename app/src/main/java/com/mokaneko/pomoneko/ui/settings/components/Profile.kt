package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.Lime
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim
import com.mokaneko.pomoneko.ui.theme.poppins

@Composable
fun SettingsProfile() {
    //Profile
    Text(
        modifier = Modifier.padding(vertical = 20.dp),
        text = "Profile",
        color = White,
        fontFamily = poppins,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = Center
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* TODO: Add save profile action */ },
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Lime,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(
                text = "Save Profile",
                color = White,
                fontFamily = itim,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Button(
            onClick = { /* TODO: Add save profile action */ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Lime,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(
                text = "Load Profile",
                color = White,
                fontFamily = itim,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}