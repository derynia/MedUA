package com.medua.presentation.pills

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medua.R
import com.medua.data.PillToTake
import com.medua.data.mockList
import com.medua.presentation.basic.OopsBox
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText
import com.medua.presentation.home.CardPills
import com.medua.ui.theme.ButtonGreen
import com.medua.ui.theme.FilterTextColor
import com.medua.ui.theme.LightRed
import com.medua.ui.theme.White

@Composable
fun PillsScreen() {
    //EmptyPillsScreen()
    PillsList()
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PillsList() {
    val pillsList = remember {
        mutableStateListOf<PillToTake>()
    }
    mockList.map { pillsList.add(it) }
    val directions = setOf(
        DismissDirection.StartToEnd,
        DismissDirection.EndToStart
    )

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
                text = stringResource(id = R.string.drink_it_all),
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
            FilterTabs()
            LazyColumn {
                items(pillsList, { pillsList: PillToTake -> pillsList.id }) {
                    val dismissState = rememberDismissState(initialValue = DismissValue.Default)

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier.fillMaxWidth().align(Alignment.End),
                        directions = directions,
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )

                            val alignment = when (direction) {
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent)
                                    .padding(start = 12.dp, end = 12.dp),
                                contentAlignment = alignment
                            ) {
                                when (direction) {
                                    DismissDirection.StartToEnd -> PillsButton(
                                        R.drawable.checkmark,
                                        R.string.accepted,
                                        Modifier
                                            .width(106.dp)
                                            .padding(start = 12.dp, end = 12.dp, top = 16.dp),
                                        ButtonGreen
                                    ) {}
                                    DismissDirection.EndToStart -> PillsButton(
                                        R.drawable.xmark,
                                        R.string.i_forgot,
                                        Modifier
                                            .width(88.dp)
                                            .padding(start = 12.dp, end = 12.dp, top = 16.dp),
                                        LightRed
                                    ) {}
                                }
                            }
                        },
                        dismissContent = {
                            CardPills(pillToTake = it) {}
                        })
                }
            }
        }
    }
}

@Composable
fun FilterTabs() {
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
                onClick = { tabIndex = index },
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
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick }
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
                modifier = Modifier.padding(top = 26.dp),
                contentDescription = stringResource(id = caption),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(id = caption),
                color = White,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun FilterTabsPreview() {
    PillsList()
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun Button() {
    Column() {
        PillsButton(R.drawable.xmark, R.string.i_forgot, Modifier.width(88.dp), LightRed) {}
        PillsButton(R.drawable.checkmark, R.string.accepted, Modifier.width(106.dp), ButtonGreen) {}
    }
}