package com.medua.presentation.basic

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleText(@StringRes title: Int, modifier: Modifier) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(top = 8.dp, bottom = 16.dp)
            .fillMaxWidth()
    )
}