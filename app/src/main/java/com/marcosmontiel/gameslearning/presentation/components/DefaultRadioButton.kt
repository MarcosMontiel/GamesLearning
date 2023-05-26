package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.screens.new_post.components.Category

@Composable
fun DefaultRadioButton(modifier: Modifier = Modifier, item: Category) {
    Row(
        modifier = modifier.selectable(selected = false, onClick = {}),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = false,
            onClick = { }
        )

        DefaultIconRes(
            size = 20.dp,
            drawable = item.icon
        )

        Spacer(modifier = Modifier.size(8.dp))

        DefaultText(
            text = item.name,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2
        )

    }
}
