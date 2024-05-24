package com.sachidanand.storemanagement.presentation.components.dialog

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.domain.model.Priority
import com.sachidanand.storemanagement.domain.model.StoreItem
import com.sachidanand.storemanagement.presentation.components.editText.editTextView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addItemBottomPopup(onDismiss: () -> Unit): StoreItem {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var itemName by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var storeItem = StoreItem()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        //CountryList()
        itemName = editTextView(
            modifier = Modifier,
            placeholder = stringResource(id = R.string.new_item),
            hint = "${stringResource(id = R.string.enter)} ${
                stringResource(
                    id = R.string.new_item
                )
            }", ""
        )
        description = editTextView(
            modifier = Modifier,
            placeholder = stringResource(id = R.string.description),
            hint = "${stringResource(id = R.string.enter)} ${
                stringResource(
                    id = R.string.description
                )
            }", ""
        )
        quantity = editTextView(
            modifier = Modifier,
            placeholder = stringResource(id = R.string.quantity),
            hint = "${stringResource(id = R.string.enter)} ${
                stringResource(
                    id = R.string.quantity
                )
            }", "",
            KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current.applicationContext
            Button(
                onClick = {
                    if (itemName.isNotEmpty() && description.isNotEmpty() && quantity.isNotEmpty()) {
                        storeItem = StoreItem(
                            itemId = 3,
                            itemName = itemName,
                            itemDescription = description,
                            priority = Priority.Low,
                            createdOn = 340,
                            quantity = 5,
                            true
                        )
                    } else {
                        Toast.makeText(
                            context,
                            "All fields are mandatory $itemName des- $description",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },

                colors = ButtonDefaults.textButtonColors(Color.Blue),
                modifier = Modifier.clickable(enabled = true) {}
            ) {
                Text(text = stringResource(id = R.string.add_item), color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

    }
    return storeItem
}

@Composable
fun CountryList() {
    val countries = listOf(
        Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    )

    LazyColumn {
        items(countries) { (country, flag) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = flag,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(text = country)
            }
        }
    }
}