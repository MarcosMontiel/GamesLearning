package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.screens.new_post.Category
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray400
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500

@Composable
fun DefaultRadioButton(
    modifier: Modifier = Modifier,
    item: Category,
    isSelected: Boolean,
    onValueChangeAction: (String) -> Unit
) {
    Row(
        modifier = modifier.selectable(
            selected = isSelected,
            onClick = {
                onValueChangeAction(item.name)
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = isSelected,
            onClick = {
                onValueChangeAction(item.name)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Gray200,
                unselectedColor = Gray400,
                disabledColor = Gray500
            )
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