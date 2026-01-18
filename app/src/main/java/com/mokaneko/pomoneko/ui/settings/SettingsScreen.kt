package com.mokaneko.pomoneko.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mokaneko.pomoneko.ui.common.icons.BackChevronIcon
import com.mokaneko.pomoneko.ui.common.icons.ResetIcon
import com.mokaneko.pomoneko.ui.settings.components.AdditionalComponents
import com.mokaneko.pomoneko.ui.settings.components.DurationComponents
import com.mokaneko.pomoneko.ui.settings.components.SettingsSessionCounts
import com.mokaneko.pomoneko.ui.settings.components.Switch
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.poppins

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    var sessionCount by rememberSaveable { mutableStateOf(4) }

    Box(
        modifier = Modifier
            .background(Green)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    contentColor = White
                )
            ) {
                BackChevronIcon()
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 40.dp, bottom = 1.dp, start = 30.dp, end = 30.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /* ~~~~~~~~~~~~~ Durations ~~~~~~~~~~~~ */
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Durations",
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
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DurationComponents(
                        duration = uiState.focusDuration,
                        onPlusClick = { viewModel.updateFocusDuration(+1) },
                        onMinusClick = { viewModel.updateFocusDuration(-1)}
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Focus",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DurationComponents(
                        duration = uiState.shortBreakDuration,
                        onPlusClick = { viewModel.updateShortBreakDuration(+1) },
                        onMinusClick = { viewModel.updateShortBreakDuration(-1)}
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Short Break",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DurationComponents(
                        duration = uiState.longBreakDuration,
                        onPlusClick = { viewModel.updateLongBreakDuration(+1) },
                        onMinusClick = { viewModel.updateLongBreakDuration(-1)}
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Long Break",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /* TODO: Add click action */ },
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SemiTransparent,
                        contentColor = White
                    )
                ) {
                    ResetIcon(modifier = Modifier.size(40.dp))
                }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Reset Duration",
                    color = White,
                    fontFamily = poppins,
                    fontSize = 14.sp,
                    textAlign = Center,
                    fontWeight = FontWeight.Medium
                )
            }

            /* ~~~~~~~~~~~~~ Session Counts ~~~~~~~~~~~~ */
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Session Counts",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            SettingsSessionCounts(
                sessionCount = sessionCount,
                onSessionChange = { sessionCount = it }
            )
            /* ~~~~~~~~~~~~~ Others ~~~~~~~~~~~~ */
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Others",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            Switch("Auto Start Focus")
            Spacer(modifier = Modifier.height(15.dp))
            Switch("Auto Start Break")
            Spacer(modifier = Modifier.height(15.dp))
            Switch("Vibration")
            Spacer(modifier = Modifier.height(15.dp))
            Switch("Stay Awake")

            /* ~~~~~~~~~~~~~ Reset ~~~~~~~~~~~~ */
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Reset To Default",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            Button(
                onClick = { /* TODO: Add reset to default action */ },
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink,
                    contentColor = White
                )
            ) {
                ResetIcon(modifier = Modifier.size(40.dp))
            }

            /* ~~~~~~~~~~~~~ Additional ~~~~~~~~~~~~~~*/
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Additional",
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
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AdditionalComponents(text = "?")
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "What is \npomodoro",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )

                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AdditionalComponents(text = "?")
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "How to \nuse",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AdditionalComponents(text = "Rate", fontSize = 20)
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Rate \nus",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            //made with love
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 20.dp),
                text = "Created with ❤\uFE0F by mokaneko",
                color = White,
                fontFamily = poppins,
                fontSize = 10.sp,
                textAlign = Center,
                fontWeight = FontWeight.Medium
            )

        }
    }
}


@Composable
@Preview
fun OptionPreview() {
    SettingsScreen(onBack = {})
}