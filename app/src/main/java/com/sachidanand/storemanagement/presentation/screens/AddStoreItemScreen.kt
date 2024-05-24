package com.sachidanand.storemanagement.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.presentation.components.appBarComponents.TopAppBarWithBackButton
import com.sachidanand.storemanagement.presentation.components.editText.editTextView

@Composable
fun AddStoreItemScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (tvHeading, etItemName, etItemDescription, etItemQuantity, btnAdd, clContent) = createRefs()
        TopAppBarWithBackButton(
            title = stringResource(R.string.add_item),
            modifier = Modifier.constrainAs(tvHeading) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }, onClick = {
                navController.popBackStack()
            }
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .constrainAs(
                    clContent
                ) {
                    top.linkTo(tvHeading.bottom)
                }
        ) {
            var itemName by rememberSaveable { mutableStateOf("") }
            var description by rememberSaveable { mutableStateOf("") }
            var quantity by rememberSaveable { mutableStateOf("") }

            itemName = editTextView(
                modifier = Modifier
                    .constrainAs(etItemName) {
                        top.linkTo(tvHeading.bottom)
                        start.linkTo(parent.start)
                    },
                placeholder = stringResource(id = R.string.new_item),
                hint = "${stringResource(id = R.string.enter)} ${
                    stringResource(
                        id = R.string.new_item
                    )
                }", ""
            )
            description = editTextView(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(etItemDescription) {
                        top.linkTo(etItemName.bottom)
                        start.linkTo(parent.start)
                    },
                placeholder = stringResource(id = R.string.description),
                hint = "${stringResource(id = R.string.enter)} ${
                    stringResource(
                        id = R.string.description
                    )
                }", ""
            )
            quantity = editTextView(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(etItemQuantity) {
                        top.linkTo(etItemDescription.bottom)
                        start.linkTo(parent.start)
                    },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .constrainAs(btnAdd) {
                        top.linkTo(etItemQuantity.bottom)
                        start.linkTo(parent.start)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current.applicationContext
                Button(
                    onClick = {
                        if (itemName.isNotEmpty() && description.isNotEmpty() && quantity.isNotEmpty()) {
                            //storeItem = StoreItem(itemId = 3, itemName = itemName, itemDescription = description, priority = Priority.Low, createdOn = 340, quantity = 5, true)
                        } else {
                            Toast.makeText(
                                context,
                                "All fields are mandatory",
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
    }
}