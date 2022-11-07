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
import androidx.navigation.NavHostController
import com.medua.R
import com.medua.presentation.basic.TitleText
import com.medua.presentation.navigation.HomeScreenMenu
import com.medua.presentation.navigation.homeItems

@Composable
fun HomeScreen(navController: NavHostController) {
    TitleText(title = R.string.app_name, Modifier)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuList(navController = navController, menuItems = homeItems)
    }
}

@Composable
fun MenuList(navController: NavHostController, menuItems: List<HomeScreenMenu>) {
    LazyColumn {
        items(menuItems) {
            CardMain(homeScreenItem = it) { navController.navigate(it.route) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //HomeScreen()
}