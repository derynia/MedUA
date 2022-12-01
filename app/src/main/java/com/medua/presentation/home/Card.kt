package com.medua.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.data.PillToTake
import com.medua.data.RevealStatus
import com.medua.presentation.navigation.HomeScreenMenu
import com.medua.ui.theme.Green
import com.medua.ui.theme.LightRed
import com.medua.ui.theme.PillsTextStyle
import com.medua.ui.theme.TextGrey
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMain(homeScreenItem: HomeScreenMenu, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(bottom = 20.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 16.dp, top = 10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = homeScreenItem.iconId),
                modifier = Modifier
                    .background(
                        color = homeScreenItem.backgroundColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .width(88.dp)
                    .height(88.dp),
                contentDescription = stringResource(
                    R.string.analyzes
                ),
                tint = Color.Unspecified
            )
            Column(modifier = Modifier.padding(start = 28.dp, end = 20.dp)) {
                Text(
                    text = stringResource(id = homeScreenItem.title),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(id = homeScreenItem.description),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun PillsCard(
    pillToTake: PillToTake,
    revealStatus: RevealStatus,
    cardOffset: Float,
    onMoveLeft: () -> Unit,
    onMoveRight: () -> Unit,
    onCollapsed: () -> Unit
) {
    val transitionState = remember {
        MutableTransitionState(revealStatus).apply {
            targetState = RevealStatus.Right
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = {
            when (it) {
                RevealStatus.Right -> cardOffset
                RevealStatus.Left -> -cardOffset
                else -> 0f
            }
        }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .offset { IntOffset(offsetTransition.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onMoveRight()
                        -dragAmount >= MIN_DRAG_AMOUNT -> onMoveLeft()
                        dragAmount < -MIN_DRAG_AMOUNT -> onCollapsed()
                    }
                }
            },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = pillToTake.pillType.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .height(22.dp)
        )
        Text(
            text = "${pillToTake.timesADay} ${stringResource(id = R.string.times)}",
            style = PillsTextStyle,
            modifier = Modifier
                .padding(top = 2.dp, start = 16.dp)
                .height(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (pillToTake.taken) Green else LightRed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.padding(6.dp),
                    painter = painterResource(id = if (pillToTake.taken) R.drawable.checkmark else R.drawable.xmark),
                    contentDescription = stringResource(R.string.checkmark)
                )
            }
            Text(
                text = stringResource(id = if (pillToTake.taken) R.string.already_accepted else R.string.forgot_to_accept),
                style = PillsTextStyle,
                color = TextGrey,
                modifier = Modifier.padding(start = 11.dp)
            )
        }
    }
}