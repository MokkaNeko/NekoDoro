package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.common.icons.CheckMarkIcon
import com.mokaneko.pomoneko.ui.common.icons.CrossMarkIcon
import com.mokaneko.pomoneko.ui.theme.Lime
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.Transparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun Switch(
    text: String
) {
    var checked by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .background(SemiTransparent, shape = RoundedCornerShape(10))
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = White,
                fontFamily = itim,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Switch(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = SwitchDefaults.colors(checkedTrackColor = Pink, uncheckedTrackColor = SemiTransparent, uncheckedBorderColor = White, uncheckedThumbColor = White)
            )
        }
    }
}

@Composable
@Preview
fun PreviewSwitch() {
    Switch("Test")
}