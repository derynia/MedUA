package com.medua.presentation.pills

import androidx.compose.foundation.layout.*
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
import com.medua.presentation.basic.OopsBox
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText
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
            )
        }
        SearchField()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterTabs()
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
            Tab(
                selected = selected,
                onClick = { tabIndex = index },
            ) {
                Text(
                    text = text,
                    color = if (selected) MaterialTheme.colorScheme.primary else FilterTextColor,
                )
                Icon(
                    painter = painterResource(
                        id = if (selected) R.drawable.select_rectangle else R.drawable.unselect_rectangle
                    ),
                    modifier = Modifier.padding(top = 10.dp),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterTabsPreview() {
    FilterTabs()
}