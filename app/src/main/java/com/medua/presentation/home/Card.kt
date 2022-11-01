package com.medua.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.medua.R
import com.medua.ui.theme.Pink

@Composable
fun CardMain() {
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
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.analyzes),
                modifier = Modifier
                    .background(color = Pink, shape = RoundedCornerShape(16.dp))
                    .width(88.dp)
                    .height(88.dp),
                contentDescription = stringResource(
                    R.string.analyzes
                ),
                tint = Color.Unspecified
            )
        }
    }
}
