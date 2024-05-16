package com.sachidanand.storemanagement.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.domain.model.Priority
import com.sachidanand.storemanagement.domain.model.StoreItem
import com.sachidanand.storemanagement.presentation.components.EmptyStore
import com.sachidanand.storemanagement.presentation.components.StoreView

/**
 * Created by Zaki on 05-10-2023.
 */
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier.padding(16.dp)
    ) {
        val (tvHeading, tvSubHeading, clEmpty, storeList) = createRefs()
        val stores = listOf(
            StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
            StoreItem(
                2,
                "Alex syrup",
                "Syrup",
                Priority.Low,
                340L,
                10,
                false
            )
        )
        Text(
            text = stringResource(R.string.your_Store),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .constrainAs(tvHeading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        if (stores.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(storeList) {
                        top.linkTo(tvHeading.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
            ) {
                items(stores) { store ->
                    StoreView(store = store)
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
    }
}

