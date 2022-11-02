package com.medua.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.presentation.navigation.HomeScreenMenu
import com.medua.presentation.navigation.homeItems

@Composable
fun HomeScreen() {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    )
    Column(
        modifier = Modifier.fillMaxHeight().padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuList(menuItems = homeItems)
    }
}

@Composable
fun MenuList(menuItems: List<HomeScreenMenu>) {
    LazyColumn {
        items(menuItems) {
            CardMain(homeScreenItem = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}