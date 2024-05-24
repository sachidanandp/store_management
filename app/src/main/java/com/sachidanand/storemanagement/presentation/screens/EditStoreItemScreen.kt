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
import com.sachidanand.storemanagement.presentation.components.dropDown.CustomDropDown
import com.sachidanand.storemanagement.presentation.components.appBarComponents.TopAppBarWithBackButton
import com.sachidanand.storemanagement.presentation.components.customSwitch.SwitchWithIcon
import com.sachidanand.storemanagement.presentation.components.editText.editTextView
import com.sachidanand.storemanagement.utils.PriorityList

@Composable
fun EditStoreItemScreen(
    navController: NavController,
    storeName: String?,
    itemId: Int,
    itemDescription: String?,
    itemQuantity: Int,
    createdOn: Long,
    inStore: Boolean,
    priority: String?
) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (tvHeading, etItemName, etItemDescription, etItemQuantity, btnAdd, clContent, dropDownPriority, inStoreSwitch) = createRefs()
        TopAppBarWithBackButton(title = stringResource(R.string.product_edit),
            modifier = Modifier.constrainAs(tvHeading) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            onClick = {
                navController.popBackStack()
            })
        ConstraintLayout(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .constrainAs(clContent) {
                top.linkTo(tvHeading.bottom, 30.dp)
                bottom.linkTo(parent.bottom)
            }) {

            var itemName by rememberSaveable { mutableStateOf("") }
            var description by rememberSaveable { mutableStateOf("") }
            var quantity by rememberSaveable { mutableStateOf("") }
            var priorityMutable by rememberSaveable { mutableStateOf(priority.toString()) }
            var inStoreMutable by rememberSaveable { mutableStateOf(inStore) }

            itemName = editTextView(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(etItemName) {
                        top.linkTo(tvHeading.bottom)
                        start.linkTo(parent.start)
                    },
                placeholder = stringResource(id = R.string.new_item),
                hint = "${stringResource(id = R.string.enter)} ${
                    stringResource(
                        id = R.string.new_item
                    )
                }", storeName.toString()
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
                }",
                itemDescription.toString()
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
                }",
                itemQuantity.toString(),
                KeyboardType.Number
            )

            CustomDropDown(selectedValue = priorityMutable,
                options = PriorityList.prioritylist,
                label = stringResource(
                    id = R.string.priority
                ),
                onValueChangedEvent = {
                    priorityMutable = it
                },
                modifier = Modifier
                    .padding(3.dp)
                    .constrainAs(dropDownPriority) {
                        top.linkTo(etItemQuantity.bottom)
                        start.linkTo(etItemQuantity.start)
                        end.linkTo(etItemQuantity.end)
                    }
            )

            Row(modifier = Modifier
                .padding(3.dp)
                .constrainAs(inStoreSwitch) {
                    top.linkTo(dropDownPriority.bottom)
                    start.linkTo(dropDownPriority.start)
                    end.linkTo(dropDownPriority.end)
                }) {

                Text(
                    text = if (inStoreMutable) "In Stock" else "Out Of Stock",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                SwitchWithIcon(modifier = Modifier
                    .padding(start = 10.dp), inStoreMutable,
                    onCheckedChange = {
                        inStoreMutable = it
                    })

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .constrainAs(btnAdd) {
                        top.linkTo(inStoreSwitch.bottom)
                        start.linkTo(parent.start)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (itemName.isNotEmpty() && description
                                .isNotEmpty() && quantity.isNotEmpty()
                        ) {
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