package com.sachidanand.storemanagement.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sachidanand.storemanagement.utils.UiText

/**
 * Created by Sachidanand on 10-05-2024.
 */
@Composable
fun AnimatedAddButton(value: UiText, onClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    ExtendedFloatingActionButton(
        text = { Text(text = value.asString(), fontWeight = FontWeight.Medium) },
        icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onClick = {
            isExpanded = !isExpanded
            onClick()
        },
        modifier = Modifier
            .padding(16.dp)
            .sizeIn(minWidth = 56.dp, minHeight = 56.dp)

    )
}
