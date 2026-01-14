package com.mokaneko.pomoneko.ui.timer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
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
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim
import kotlin.concurrent.timer


@Composable
fun TimerScreen(
    uiState: TimerUiState,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
){
    Box(
        modifier = Modifier
            .background(Green)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(20.dp)
                        .width(24.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    ),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    HamburgerIcon(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                TaskName(name = uiState.taskName)
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
                contentAlignment = Alignment.Center
            ){
                CatClock(progress = uiState.progress, phase = uiState.phase, timerState = uiState.timerState)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PomodoroSection(
                    totalSection = uiState.totalSection,
                    currentSection = uiState.currentSection,
                    phase = uiState.phase
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                TaskTimer(timer = uiState.timerText)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimerControl(
                    timerState = uiState.timerState,
                    onPlay = onPlay,
                    onPause = onPause,
                    onReset = onReset
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                SectionText(currentSection = uiState.phase)
            }
        }

    }
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
fun CatClock(
    progress: Float,
    phase: PomodoroPhase,
    timerState: TimerState
) {
    val animatedProgress by animateFloatAsState(
        targetValue = when (timerState) {
            TimerState.STOPPED -> 0f
            else -> progress
        },
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "TimerProgress"
    )
    val targetColor = when {
        timerState == TimerState.STOPPED -> Inactive
        phase != PomodoroPhase.FOCUS -> Inactive
        else -> White
    }
    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(
            durationMillis = 600,
            easing = LinearOutSlowInEasing
        ),
        label = "CatColor"
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        val clockSize = maxWidth * 0.75f
        val faceSize = clockSize * 0.86f
        val earWidth = clockSize * 1.5f
        val earOffsetY = -(clockSize * 0.42f)
        Box(contentAlignment = Alignment.Center) {
            if (phase == PomodoroPhase.FOCUS) {
                TimerCircleBorder(
                    progress = animatedProgress,
                    modifier = Modifier.size(clockSize),
                    strokeWidth = 18.dp
                )
            } else {
                TimerCircleBorder(
                    progress = animatedProgress,
                    modifier = Modifier.size(clockSize),
                    strokeWidth = 18.dp,
                    color1 = Inactive,
                    color2 = White,
                    whiteSweep = 360f - 360f * progress,
                    greySweep = 360f * progress
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_cat_face),
                contentDescription = null,
                modifier = Modifier.size(faceSize),
                colorFilter = ColorFilter.tint(animatedColor)
            )

            Image(
                painter = painterResource(R.drawable.ic_cat_ear),
                contentDescription = null,
                modifier = Modifier
                    .size(earWidth)
                    .offset(y = earOffsetY),
                colorFilter = ColorFilter.tint(animatedColor)
            )
        }
    }
}

/*------------------- Timer Number -------------------*/
@Composable
fun TaskTimer(timer: String) {
    Text(
        modifier = Modifier
            .layoutId("taskTimer"),
        text = timer,
        color = White,
        fontFamily = itim,
        fontSize = 28.sp,
        textAlign = Center
    )
}

/*------------------- Pomodoro Section -------------------*/
@Composable
fun PomodoroSection(
    totalSection: Int,
    currentSection: Int,
    phase: PomodoroPhase,
    circleSize: Dp = 36.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSection) { index ->
            val sectionNumber = index + 1

            val color = when {
                sectionNumber < currentSection -> White // completed
                sectionNumber == currentSection -> {
                    when (phase) {
                        PomodoroPhase.FOCUS -> White
                        PomodoroPhase.SHORT_BREAK,
                        PomodoroPhase.LONG_BREAK -> Pink
                    }
                }
                else -> Inactive // not started
            }
            Canvas(
                modifier = Modifier
                    .size(circleSize)
                    .padding(horizontal = 6.dp)
            ) {
                drawCircle(color = color)
            }
        }
    }
}

/*------------------- Section Text -------------------*/
@Composable
fun SectionText(currentSection: PomodoroPhase) {
    val section: String = when(currentSection) {
        PomodoroPhase.FOCUS -> "Focus"
        PomodoroPhase.SHORT_BREAK -> "Short Break"
        PomodoroPhase.LONG_BREAK -> "Long Break"
    }
    Text(
        modifier = Modifier
            .layoutId("sectionText"),
        text = section,
        color = White,
        fontFamily = itim,
        fontSize = 24.sp,
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
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 60.dp),
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

/*------------------- Icons -------------------*/
@Composable
fun TimerCircleBorder(
    progress: Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    color1: Color = White,
    color2: Color = Inactive,
    whiteSweep: Float = 360f * progress,
    greySweep: Float = 360f - whiteSweep
) {
    Canvas(
        modifier = modifier.aspectRatio(1f)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx()
        )
        val startAngle = 90f
        drawArc(
            color = color2,
            startAngle = startAngle + whiteSweep,
            sweepAngle = greySweep,
            useCenter = false,
            style = stroke
        )
        drawArc(
            color = color1,
            startAngle = startAngle,
            sweepAngle = whiteSweep,
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
        modifier = modifier.size(25.dp)
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
    color: Color = White,
) {
    Canvas(
        modifier = modifier.size(25.dp)
    ) {
        val barWidth = size.width * 0.25f
        val gap = size.width * 0.15f
        val totalWidth = barWidth*2 + gap
        val startX = (size.width - totalWidth) / 2

        drawRect(
            color = color,
            topLeft = Offset(startX, 0f),
            size = Size(barWidth, size.height)
        )
        drawRect(
            color = color,
            topLeft = Offset(startX + barWidth + gap, 0f),
            size = Size(barWidth, size.height)
        )
    }
}
@Composable
fun ResetIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(modifier = modifier.size(20.dp)) {
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
    modifier: Modifier = Modifier,
    color: Color = White,
    strokeWidth: Float = 12f
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
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

@Composable
fun HamburgerIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    strokeWidth: Float = 2f
) {
    Canvas(
        modifier = modifier
            .size(width = 20.dp, height = 10.dp)
    ) {
        val strokePx = strokeWidth.dp.toPx()

        val startX = strokePx / 2
        val endX = size.width - strokePx / 2

        drawLine(
            color = color,
            start = Offset(startX, strokePx / 2),
            end = Offset(endX, strokePx / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )

        drawLine(
            color = color,
            start = Offset(startX, size.height / 2),
            end = Offset(endX, size.height / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )

        drawLine(
            color = color,
            start = Offset(startX, size.height - strokePx / 2),
            end = Offset(endX, size.height - strokePx / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )
    }
}


/*------------------- End of Icon -------------------*/

/*------------------- Preview -------------------*/
private val PreviewTimerState = TimerUiState(
    taskName = "This cat needs a name",
    timerText = "25:00",
    currentSection = 4,
    totalSection = 4,
    phase = PomodoroPhase.LONG_BREAK,
    timerState = TimerState.RUNNING,
    progress = 0f
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
