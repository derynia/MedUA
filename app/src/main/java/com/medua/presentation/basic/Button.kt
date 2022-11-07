package com.medua.presentation.basic

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.medua.ui.theme.White

@Composable
fun BasicButton(modifier: Modifier, @StringRes caption: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .size(width = 256.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(id = caption),
            style = MaterialTheme.typography.titleMedium,
            color = White
        )
    }
}