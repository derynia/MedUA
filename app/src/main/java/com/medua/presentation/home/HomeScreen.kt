package com.medua.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.presentation.basic.TitleText
import com.medua.presentation.navigation.HomeScreenMenu
import com.medua.presentation.navigation.homeItems

@Composable
fun HomeScreen() {
    TitleText(title = R.string.app_name)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 40.dp),
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
            CardMain(homeScreenItem = it) { navigateToScreen(it) }
        }
    }
}

fun navigateToScreen(screenItem: HomeScreenMenu) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}