package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue300
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    backgroundColor: Color = Blue500,
    contentColor: Color = Gray200,
    disabledBackground: Color = Blue300,
    disabledContentColor: Color = Gray200,
    title: String,
    fontWeight: FontWeight = FontWeight.Bold,
    style: TextStyle = MaterialTheme.typography.body1,
    onClickAction: () -> Unit
) {
    Button(
        onClick = {
            onClickAction()
        },
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackground,
            disabledContentColor = disabledContentColor
        )
    ) {
        DefaultText(
            text = title,
            fontWeight = fontWeight,
            style = style
        )
    }
}
