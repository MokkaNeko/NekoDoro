package com.mokaneko.pomoneko.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.theme.Lime
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim
import com.mokaneko.pomoneko.ui.theme.poppins
import com.mokaneko.pomoneko.ui.timer.ResetIcon
import com.mokaneko.pomoneko.ui.timer.SwipeUpIcon

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Green)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
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
                SwipeDown(modifier = Modifier)
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
            //Durations
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
                    Button(
                        onClick = { /* TODO: Add click action */ },
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "25",
                            color = White,
                            fontFamily = itim,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Focus",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 16.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
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
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "25",
                            color = White,
                            fontFamily = itim,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Short Break",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 16.sp,
                        textAlign = Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = 10.dp),
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
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "25",
                            color = White,
                            fontFamily = itim,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Long Break",
                        color = White,
                        fontFamily = poppins,
                        fontSize = 16.sp,
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
                        containerColor = Lime,
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
                    fontSize = 16.sp,
                    textAlign = Center,
                    fontWeight = FontWeight.Medium
                )
            }

            //Pomodoro Counts
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Pomodoro Counts",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            Box(
                modifier = Modifier
                    .background(Lime, shape = RoundedCornerShape(10))
                    .height(90.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    color = White,
                    fontFamily = itim,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            //Auto Start
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Auto Start",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            Box(
                modifier = Modifier
                    .background(Lime, shape = RoundedCornerShape(10))
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
                        text = "Auto Start Pomodoro",
                        color = White,
                        fontFamily = itim,
                        fontSize = 18.sp,
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
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(Lime, shape = RoundedCornerShape(10))
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
                        text = "Auto Start Break",
                        color = White,
                        fontFamily = itim,
                        fontSize = 18.sp,
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

            //Notification
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Notification",
                color = White,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = Center
            )
            Box(
                modifier = Modifier
                    .background(Lime, shape = RoundedCornerShape(10))
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
                        text = "Vibration",
                        color = White,
                        fontFamily = itim,
                        fontSize = 18.sp,
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
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(Lime, shape = RoundedCornerShape(10))
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
                        text = "Alarm",
                        color = White,
                        fontFamily = itim,
                        fontSize = 18.sp,
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

            //Reset to Default
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
            )  {
                ResetIcon(modifier = Modifier.size(40.dp))
            }

            //Others
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Others",
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
                    Button(
                        onClick = { /* TODO: Add click action */ },
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "?",
                            color = White,
                            fontFamily = itim,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                    Button(
                        onClick = { /* TODO: Add click action */ },
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "?",
                            color = White,
                            fontFamily = itim,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                    Button(
                        onClick = { /* TODO: Add click action */ },
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Lime,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Rate",
                            color = White,
                            fontFamily = itim,
                            fontSize = 20.sp
                        )
                    }
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
fun SwipeDown(modifier: Modifier) {
    SwipeUpIcon()
}

@Composable
@Preview
fun OptionPreview() {
    SettingsScreen(onBack = {})
}