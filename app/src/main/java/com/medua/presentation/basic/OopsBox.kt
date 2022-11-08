package com.medua.presentation.basic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.ui.theme.OopsStyle

@Composable
fun OopsBox(@DrawableRes iconId: Int, @StringRes contentDescription: Int, @StringRes text: Int) {
    Icon(
        painter = painterResource(id = iconId), contentDescription = stringResource(
            contentDescription
        ), tint = Color.Unspecified
    )
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = stringResource(R.string.oops),
        style = OopsStyle,
    )
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 86.dp, end = 86.dp)
    )
}

@Composable
fun OopsBoxWithButton(
    @DrawableRes iconId: Int,
    @StringRes contentDescription: Int,
    @StringRes text: Int,
    @StringRes buttonCaption: Int,
    onClick: () -> Unit
) {
    OopsBox(iconId = iconId, contentDescription = contentDescription, text = text)
    BasicButton(modifier = Modifier.padding(top = 101.dp), caption = buttonCaption, onClick)
}