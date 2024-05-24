package com.sachidanand.storemanagement.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.domain.model.StoreItem
import com.sachidanand.storemanagement.presentation.components.AnimatedAddButton
import com.sachidanand.storemanagement.presentation.components.EmptyStore
import com.sachidanand.storemanagement.presentation.components.StoreView
import com.sachidanand.storemanagement.presentation.components.appBarComponents.TopAppBarWithoutBackButton
import com.sachidanand.storemanagement.presentation.navigation.Screens
import com.sachidanand.storemanagement.presentation.navigation.ScreensObject
import com.sachidanand.storemanagement.utils.UiText

/**
 * Created by Sachidanand on 05-05-2024.
 */
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier, stores: List<StoreItem>) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (tvHeading, tvSubHeading, clEmpty, storeList, fbAddItem, clContent) = createRefs()
        TopAppBarWithoutBackButton(
            title = stringResource(R.string.your_Store),
            modifier = Modifier.constrainAs(tvHeading) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }, onClick = {
                Toast.makeText(context, "To do", Toast.LENGTH_SHORT).show()
            }
        )
        ConstraintLayout(
            modifier = modifier
                .padding(16.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .constrainAs(clContent) {
                    top.linkTo(tvHeading.bottom)
                    bottom.linkTo(parent.bottom)
                }
        ) {

            if (stores.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 16.dp)
                        .constrainAs(storeList) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                ) {
                    items(stores) { store ->
                        StoreView(store = store, onClick = {
                            navController.navigate(
                                ScreensObject.DetailStoreItemScreen(
                                    itemId = store.itemId,
                                    itemName = store.itemName,
                                    itemDescription = store.itemDescription,
                                    priority = "${store.priority}",
                                    createdOn = store.createdOn,
                                    quantity = store.quantity,
                                    inStore = store.inStore
                                )
                            )
                        })
                    }
                }
            } else {
                EmptyStore(
                    modifier = modifier
                        .constrainAs(clEmpty) {
                            top.linkTo(tvSubHeading.bottom)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }

            AnimatedAddButton(
                value = UiText.StringResource(R.string.new_item),
                onClick = {
                    navController.navigate(Screens.AddStoreItem.route)
                }, modifier = Modifier
                    .padding(bottom = 16.dp)
                    .sizeIn(minWidth = 56.dp, minHeight = 56.dp)
                    .constrainAs(fbAddItem)
                    {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

