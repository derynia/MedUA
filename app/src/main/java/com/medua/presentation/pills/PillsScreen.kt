package com.medua.presentation.pills

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medua.R
import com.medua.data.PillToTake
import com.medua.data.RevealStatus
import com.medua.presentation.basic.BasicButton
import com.medua.presentation.basic.OopsBox
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText
import com.medua.presentation.home.PillsCard
import com.medua.ui.theme.*
import com.medua.utils.dp

const val CARD_OFFSET = 90f
const val CARD_OFFSET_END = 75f

@Composable
fun PillsScreen(viewModel: PillsViewModel) {
    val pillsToTake by viewModel.pillsToTakeData.collectAsState()
    val movedPills by viewModel.movedPillsToTakeData.collectAsState()

    if (pillsToTake.isEmpty()) EmptyPillsScreen()
    else PillsList(viewModel, pillsToTake, movedPills)
}

@Composable
fun EmptyPillsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(title = R.string.pills, Modifier)
        SearchField()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OopsBox(R.drawable.pills_oops, R.string.oops, R.string.nothing_to_take)
        }
    }
}

@Composable
fun PillsList(
    viewModel: PillsViewModel,
    pillsToTake: List<PillToTake>,
    movedPills: List<Pair<Int, RevealStatus>>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
        ) {
            Spacer(modifier = Modifier.weight(3f))
            TitleText(title = R.string.pills, Modifier.weight(6f))
            Text(
                text = stringResource(id = R.string.plus_sign),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .weight(3f)
                    .clickable { }
            )
        }
        SearchField()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterTabs(viewModel)
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(pillsToTake, PillToTake::id) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(94.dp)
                    ) {
                        PillsButton(
                            R.drawable.checkmark,
                            R.string.accepted,
                            Modifier
                                .width(106.dp)
                                .padding(start = 17.dp, end = 12.dp, top = 16.dp)
                                .clickable { viewModel.acceptPill(it) },
                            ButtonGreen,
                        )
                        PillsButton(
                            R.drawable.xmark,
                            R.string.i_forgot,
                            Modifier
                                .width(95.dp)
                                .padding(start = 12.dp, end = 17.dp, top = 16.dp)
                                .align(alignment = Alignment.TopEnd)
                                .clickable { viewModel.forgotPill(it) },
                            Orange
                        )
                        PillsCard(
                            pillToTake = it,
                            revealStatus = movedPills.firstOrNull { lookItem -> lookItem.first == it.id }?.second
                                ?: RevealStatus.None,
                            cardOffset = CARD_OFFSET.dp(),
                            cardOffsetEnd = CARD_OFFSET_END.dp(),
                            onMoveLeft = { viewModel.onItemMoved(it, RevealStatus.Left) },
                            onMoveRight = { viewModel.onItemMoved(it, RevealStatus.Right) },
                            onCollapsed = { viewModel.onCollapsed(it) }
                        )
                    }
                }
            }
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 44.dp, bottom = 20.dp),
                caption = R.string.take_it_all
            ) {}
        }
    }
}

@Composable
fun FilterTabs(viewModel: PillsViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabData = listOf(
        stringResource(R.string.morning),
        stringResource(R.string.dinner),
        stringResource(R.string.evening),
    )

    TabRow(
        selectedTabIndex = tabIndex,
        containerColor = Color.Unspecified,
        contentColor = FilterTextColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 19.dp),
        indicator = {},
        divider = {}
    ) {
        tabData.forEachIndexed { index, text ->
            val selected = tabIndex == index
            val dataAlign = when (index) {
                0 -> Alignment.Start
                tabData.size - 1 -> Alignment.End
                else -> Alignment.CenterHorizontally
            }

            Tab(
                selected = selected,
                onClick = {
                    tabIndex = index
                    viewModel.filterByTakeTime(index)
                },
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.align(dataAlign),
                        color = if (selected) MaterialTheme.colorScheme.primary else FilterTextColor,
                    )
                    Icon(
                        painter = painterResource(
                            id = if (selected) R.drawable.select_rectangle else R.drawable.unselect_rectangle
                        ),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(dataAlign),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}

@Composable
fun PillsButton(
    @DrawableRes icon: Int,
    @StringRes caption: Int,
    modifier: Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .height(94.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = color)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.padding(top = 20.dp),
                contentDescription = stringResource(id = caption),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(id = caption),
                color = White,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                maxLines = 1
            )
        }
    }
}