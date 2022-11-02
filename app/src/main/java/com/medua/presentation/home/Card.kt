package com.medua.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.presentation.navigation.HomeScreen
import com.medua.ui.theme.TextGrey

@Composable
fun CardMain(homeScreenItem: HomeScreen) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(bottom = 20.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
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
                    color = TextGrey,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
