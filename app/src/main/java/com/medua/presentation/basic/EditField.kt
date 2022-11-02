package com.medua.presentation.basic

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medua.R
import com.medua.ui.theme.InputFieldTextStyle
import com.medua.ui.theme.TextGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        textStyle = InputFieldTextStyle,
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(36.dp),
        maxLines = 1,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = textState.text,
                contentPadding = PaddingValues(),
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        color = TextGrey,
                        fontSize = 17.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.looking_glass),
                        contentDescription = stringResource(
                            R.string.looking_glass
                        )
                    )
                },
                interactionSource = remember { MutableInteractionSource() },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    )
}
