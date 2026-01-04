package com.mokaneko.pomoneko.ui.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mokaneko.pomoneko.R
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun TimerScreen(
    uiState: TimerUiState,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {

    /*------------------- Constraint Start -------------------*/
    val constraints = ConstraintSet {

        val taskName = createRefFor("taskName")
        val catClock = createRefFor("catClock")
        val taskTimer = createRefFor("taskTimer")
        val pomodoroSection = createRefFor("pomodoroSection")
        val sectionText = createRefFor("sectionText")
        val timerControl = createRefFor("timerControl")
        val swipeMenu = createRefFor("swipeMenu")

        val topGuide = createGuidelineFromTop(0.075f)
        val bottomGuide = createGuidelineFromBottom(0.02f)

        constrain(taskName) {
            top.linkTo(topGuide)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(catClock) {
            top.linkTo(taskName.bottom, 60.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(taskTimer) {
            top.linkTo(catClock.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pomodoroSection) {
            top.linkTo(taskTimer.bottom, 12.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(sectionText) {
            top.linkTo(pomodoroSection.bottom, 12.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(timerControl) {
            top.linkTo(sectionText.bottom, 24.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(swipeMenu) {
            bottom.linkTo(bottomGuide)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
    ) {
        TaskName(name = uiState.taskName)
        CatClock()
        TaskTimer(timer = uiState.timerText)
        PomodoroSection(section = uiState.section)
        SectionText(currentSection = uiState.sectionText)
        TimerControl(
            timerState = uiState.timerState,
            onPlay = onPlay,
            onPause = onPause,
            onReset = onReset
        )
//        SwipeMenu()
    }

    /*------------------- Constraint End -------------------*/
}

/*------------------- Name -------------------*/
@Composable
fun TaskName(name: String) {
    Text(
        modifier = Modifier.layoutId("taskName"),
        text = name,
        color = White,
        fontFamily = itim,
        fontSize = 20.sp,
        textAlign = Center
    )
}

/*------------------- Clock -------------------*/
@Composable
fun CatClock() {
    Box(
        modifier = Modifier
            .layoutId("catClock")
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 16.dp)
    ) {
        TimerCircleBorder(
            modifier = Modifier.size(280.dp).align(Alignment.Center).padding(top = 10.dp),
            strokeWidth = 18.dp
        )
        Image(
            painter = painterResource(R.drawable.ic_cat_face),
            contentDescription = "Clock",
            modifier = Modifier.size(240.dp).align(Alignment.Center).padding(top = 10.dp),
            colorFilter = ColorFilter.tint(Inactive)
        )
        Image(
            painter = painterResource(R.drawable.ic_cat_ear),
            contentDescription = "Clock",
            colorFilter = ColorFilter.tint(Inactive)
        )
    }
}

/*------------------- Timer Number -------------------*/
@Composable
fun TaskTimer(timer: String) {
    Text(
        modifier = Modifier
            .layoutId("taskTimer")
            .padding(top = 20.dp),
        text = timer,
        color = White,
        fontFamily = itim,
        fontSize = 32.sp,
        textAlign = Center
    )
}

/*------------------- Pomodoro Section -------------------*/
@Composable
fun PomodoroSection(section: Int) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .layoutId("pomodoroSection")
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (section < 6) {
            for (i in 1..section) {
                Canvas(
                    modifier = Modifier
                        .size(20.dp)
                ) {
                    drawCircle(color = Inactive)
                }
            }
        } else {
            val sectionDivider = section / 2
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                if (section % 2 == 0) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (i in 1..sectionDivider) {
                            Canvas(
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                drawCircle(color = White)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (i in 1..sectionDivider) {
                            Canvas(
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                drawCircle(color = White)
                            }
                        }
                    }
                }
                else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (i in 1..sectionDivider+1) {
                            Canvas(
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                drawCircle(color = White)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (i in 1..sectionDivider) {
                            Canvas(
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                drawCircle(color = White)
                            }
                        }
                    }
                }
            }
        }
    }
}

/*------------------- Section Text -------------------*/
@Composable
fun SectionText(currentSection: String) {
    Text(
        modifier = Modifier
            .padding(top = 20.dp)
            .layoutId("sectionText"),
        text = currentSection,
        color = White,
        fontFamily = itim,
        fontSize = 32.sp,
        textAlign = Center
    )
}

/*------------------- Play or Pause -------------------*/
@Composable
fun TimerControl(
    timerState: TimerState,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Box(
        modifier = Modifier
            .layoutId("timerControl")
            .fillMaxWidth()
            .height(64.dp)
    ) {
        when (timerState) {
            TimerState.STOPPED -> {
                Button(
                    onClick = onPlay,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PlayIcon() }
            }
            TimerState.PAUSED -> {
                Button(
                    onClick = onPlay,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PlayIcon() }
                Button(
                    onClick = onReset,
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 60.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { ResetIcon() }
            }
            else -> {
                Button(
                    onClick = onPause,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PauseIcon() }
            }
        }
    }
}

/*------------------- Swipe Menu -------------------*/
@Composable
fun SwipeMenu() {
    SwipeUpIcon()
}

/*------------------- Icons -------------------*/
@Composable
fun TimerCircleBorder(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {
    Canvas(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )
        drawArc(
            color = Inactive,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = stroke
        )
    }
}
@Composable
fun PlayIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(
        modifier = modifier.size(40.dp)
    ) {
        val trianglePath = Path().apply {
            moveTo(size.width, size.height / 2f)
            lineTo(0f, 0f)
            lineTo(0f, size.height)
            close()
        }

        drawPath(
            path = trianglePath,
            color = color
        )
    }
}
@Composable
fun PauseIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(
        modifier = modifier.size(40.dp)
    ) {
        val barWidth = size.width * 0.25f
        val gap = size.width * 0.15f
        drawRect(
            color = color,
            topLeft = Offset(0f, 0f),
            size = Size(barWidth, size.height)
        )
        drawRect(
            color = color,
            topLeft = Offset(barWidth + gap, 0f),
            size = Size(barWidth, size.height)
        )
    }
}
@Composable
fun ResetIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(modifier = modifier.size(24.dp)) {
        val strokeWidth = size.width * 0.12f
        drawArc(
            color = color,
            startAngle = 30f,
            sweepAngle = -260f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        val path = Path().apply {
            moveTo(size.width * 0.8f, size.height * 0.65f)
            lineTo(size.width * 0.75f, size.height * 0.95f)
            lineTo(size.width * 1.05f, size.height * 0.85f)
            close()
        }
        drawPath(path, color = color)
    }
}
@Composable
fun SwipeUpIcon(
    color: Color = White,
) {
    Canvas(
        modifier = Modifier
            .layoutId("swipeMenu")
            .size(width = 24.dp, height = 12.dp)
    ) {
        drawPath(
            path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width / 2f, 0f)
                lineTo(size.width, size.height)
            },
            color = color,
            style = Stroke(width = 12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

/*------------------- End of Icon -------------------*/

/*------------------- Preview -------------------*/
private val PreviewTimerState = TimerUiState(
    taskName = "This cat needs a name",
    timerText = "25:00",
    section = 4,
    sectionText = "Focus",
    timerState = TimerState.STOPPED
)

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen(
        uiState = PreviewTimerState,
        onPlay = {},
        onPause = {},
        onReset = {}
    )
}
