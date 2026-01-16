package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun TaskName(
    name: String,
    onNameChange: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var text by remember(name) { mutableStateOf(name) }
    if (isEditing) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= 20) {
                    text = it
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                color = White,
                fontFamily = itim,
                fontSize = 20.sp,
                textAlign = Center
            ),
            modifier = Modifier.widthIn(min = 120.dp, max = 240.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    isEditing = false
                    onNameChange(text.ifBlank {name})
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
                focusedIndicatorColor = White,
                unfocusedIndicatorColor = Inactive,
                cursorColor = White
            )
        )
    } else {
        Text(
            modifier = Modifier.clickable { isEditing = true },
            text = name,
            color = White,
            fontFamily = itim,
            fontSize = 20.sp,
            textAlign = Center
        )
    }
}