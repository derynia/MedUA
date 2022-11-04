package com.medua.presentation.basic

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.medua.ui.theme.White

@Composable
fun BasicButton(modifier: Modifier, @StringRes caption: Int, onClick: () -> Unit) {
    Button(onClick = onClick,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .size(width = 256.dp, height = 50.dp)
    ) {
        Text(
            text = stringResource(id = caption),
            style = MaterialTheme.typography.titleMedium,
            color = White
        )
    }
}