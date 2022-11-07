package com.medua.presentation.pills

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.medua.R
import com.medua.presentation.basic.OopsBox
import com.medua.presentation.basic.SearchField
import com.medua.presentation.basic.TitleText

@Composable
fun PillsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(title = R.string.pills, Modifier)
        SearchField()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OopsBox(R.drawable.pills_oops, R.string.oops, R.string.nothing_to_take)
        }
    }
}