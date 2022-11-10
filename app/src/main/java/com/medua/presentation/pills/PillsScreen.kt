package com.medua.presentation.pills

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.data.mockList
import com.medua.presentation.basic.OopsBox
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText
import com.medua.presentation.home.CardPills
import com.medua.ui.theme.FilterTextColor

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

@Composable
fun PillsList() {
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
                items(mockList) {
                    CardPills(pillToTake = it) {}
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
                tabData.size-1 -> Alignment.End
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

@Preview
@Composable
fun FilterTabsPreview() {
    PillsList()
}