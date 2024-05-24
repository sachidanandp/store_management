package com.sachidanand.storemanagement.presentation.components.editText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun editTextView(
    modifier: Modifier,
    placeholder: String,
    hint: String,
    text: String,
    keyboardType: KeyboardType = KeyboardType.Text
): String {
    var textState by rememberSaveable { mutableStateOf(text) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = textState,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onValueChange = { textState = it },
            placeholder = { Text(text = hint) },
            label = { Text(text = placeholder) },
            singleLine = true
        )
    }

    return textState
}