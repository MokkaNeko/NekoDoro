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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.hilt.navigation.compose.hiltViewModel
import com.mokaneko.pomoneko.ui.common.icons.BackChevronIcon
import com.mokaneko.pomoneko.ui.common.icons.ResetIcon
import com.mokaneko.pomoneko.ui.settings.components.AdditionalComponents
import com.mokaneko.pomoneko.ui.settings.components.DurationComponents
import com.mokaneko.pomoneko.ui.settings.components.SettingSwitch
import com.mokaneko.pomoneko.ui.settings.components.SettingsSessionCounts
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
    val sessionCount = uiState.totalSection
    val isTimerRunning by viewModel.isTimerRunning
    var showAlert by remember {mutableStateOf(false)}

    LaunchedEffect(Unit) {
        viewModel.showResetAlert.collect {
            showAlert = true
        }
    }

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
                        onMinusClick = { viewModel.updateFocusDuration(-1)},
                        modifier = Modifier.alpha(if (isTimerRunning) 0.5f else 1f)
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
                        onMinusClick = { viewModel.updateShortBreakDuration(-1)},
                        modifier = Modifier.alpha(if (isTimerRunning) 0.5f else 1f)
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
                        onMinusClick = { viewModel.updateLongBreakDuration(-1)},
                        modifier = Modifier.alpha(if (isTimerRunning) 0.5f else 1f)
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
                    onClick = { viewModel.resetDurations() },
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp)
                        .alpha(if(isTimerRunning) 0.5f else 1f),
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
                onSessionChange = {viewModel.updateTotalSection(it)},
                modifier = Modifier.alpha(if (isTimerRunning) 0.5f else 1f)

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
            SettingSwitch(
                "Auto Start Session",
                checked = uiState.autoStartSession,
                onCheckedChange = { viewModel.updateAutoStartSession(it) }
            )
            Spacer(modifier = Modifier.height(15.dp))
            SettingSwitch(
                "Vibration",
                checked = uiState.vibrationEnabled,
                onCheckedChange = {viewModel.updateVibration(it)}
            )
            Spacer(modifier = Modifier.height(15.dp))
            SettingSwitch(
                "Stay Awake",
                checked = uiState.stayAwake,
                onCheckedChange = {viewModel.updateStayAwake(it)}
            )

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
            if (showAlert) {
                AlertDialog(
                    containerColor = SemiTransparent,
                    onDismissRequest = { showAlert = false },
                    title = {
                        Text(
                            "Timer is running",
                            fontSize = 16.sp
                        )
                    },
                    text = {
                        Text(
                            text = "Reset the timer to change the durations",
                            fontSize = 12.sp
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = { showAlert = false },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Pink
                            ),
                            shape = RoundedCornerShape(25)
                        ) {
                            Text(
                                "OK",
                                color = White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                )
            }
        }
    }
}


@Composable
@Preview
fun OptionPreview() {
    SettingsScreen(onBack = {})
}