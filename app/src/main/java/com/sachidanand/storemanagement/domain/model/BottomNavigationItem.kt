package com.sachidanand.storemanagement.domain.model

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.presentation.navigation.Screens

/**
 * Created by Sachidanand on 14-05-2024.
 */
data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun getNavigationItems(context: Context): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = context.getString(R.string.home),
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = context.getString(R.string.devices),
                icon = Icons.Filled.ShoppingCart,
                route = Screens.Calender.route
            ),
            BottomNavigationItem(
                label = context.getString(R.string.settings),
                icon = Icons.Filled.Settings,
                route = Screens.Settings.route
            ),
        )
    }
}
