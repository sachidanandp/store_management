package com.sachidanand.storemanagement.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.presentation.components.dialog.ConfirmationAlertDialog
import com.sachidanand.storemanagement.presentation.components.appBarComponents.TopAppBarWithBackAndEditDeleteButton
import com.sachidanand.storemanagement.presentation.navigation.ScreensObject
import com.sachidanand.storemanagement.utils.DateUtils

@Composable
fun DetailsStoreItemScreen(
    navController: NavController,
    modifier: Modifier,
    storeName: String?,
    itemId: Int,
    itemDescription: String?,
    quantity: Int,
    createdOn: Long,
    inStore: Boolean,
    priority: String?
) {
    val context = LocalContext.current
    val openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        ConfirmationAlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                Toast.makeText(context, "Will Delete", Toast.LENGTH_SHORT).show()
            },
            dialogTitle = stringResource(id = R.string.delete_confirmation_title),
            dialogText = stringResource(id = R.string.delete_confirmation_text),
            icon = Icons.Filled.Delete
        )
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (tvHeading, cvDetails, card, storeList, fbAddItem, clContent) = createRefs()
        TopAppBarWithBackAndEditDeleteButton(title = stringResource(R.string.product_details),
            modifier = Modifier.constrainAs(tvHeading) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            onClick = {
                navController.popBackStack()
            },
            onClickEdit = {
                navController.navigate(
                    ScreensObject.EditStoreItem(
                        itemId = itemId,
                        itemName = storeName,
                        itemDescription = itemDescription,
                        priority = priority,
                        createdOn = createdOn,
                        quantity = quantity,
                        inStore = inStore
                    )
                )
            },
            onClickDelete = {
                openAlertDialog.value = true
            }
        )

        ConstraintLayout(modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .constrainAs(clContent) {
                top.linkTo(tvHeading.bottom, 30.dp)
                bottom.linkTo(parent.bottom)
            }) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .constrainAs(cvDetails) {
                    top.linkTo(parent.top)
                }, onClick = { }) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Product Name:",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                        )

                        Text(
                            text = "$storeName",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }

                    Row(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Description:",
                            style = TextStyle(
                                fontSize = 16.sp,
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = itemDescription ?: "No name",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row {
                            Text(
                                text = "Quantity:",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                ),
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = quantity.toString(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }

                        Row {
                            Text(
                                text = "InStore:",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                ),
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = if (inStore) "Yes" else "No",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row {
                            Text(
                                text = "Priority:",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                ),
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "$priority",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }

                        Row {
                            Text(
                                text = "CreatedOn:",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                ),
                                overflow = TextOverflow.Ellipsis,
                            )

                            Text(
                                text = DateUtils.formatTime(createdOn).asString(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Id:",
                            style = TextStyle(
                                fontSize = 16.sp,
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = itemId.toString(),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
        }
    }
}