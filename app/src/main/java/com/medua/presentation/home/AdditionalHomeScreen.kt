package com.medua.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.medua.R
import com.medua.presentation.basic.OopsBoxWithButton
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText

@Composable
fun AdditionalHomeScreen(
    @StringRes title: Int,
    @DrawableRes iconId: Int,
    @StringRes contentDescription: Int,
    @StringRes text: Int,
    @StringRes buttonCaption: Int,
    navController: NavHostController,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .padding(start = 16.dp)
                .weight(3f)
                .clickable { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.Unspecified,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.back),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(2f)
                )
            }
            TitleText(title = title, Modifier.weight(7f))
            Spacer(modifier = Modifier.weight(2f))
            Icon(painter = painterResource(id = R.drawable.plus),
                contentDescription = stringResource(id = R.string.plus),
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
                    .clickable {

                    })
        }

        SearchField()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OopsBoxWithButton(iconId = iconId,
                contentDescription = contentDescription,
                text = text,
                buttonCaption = buttonCaption,
                {})
        }
    }
}
