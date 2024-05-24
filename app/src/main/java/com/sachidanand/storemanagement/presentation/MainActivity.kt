package com.sachidanand.storemanagement.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sachidanand.storemanagement.presentation.navigation.Screens
import com.sachidanand.storemanagement.domain.model.BottomNavigationItem
import com.sachidanand.storemanagement.domain.model.Priority
import com.sachidanand.storemanagement.domain.model.StoreItem
import com.sachidanand.storemanagement.presentation.components.dialog.addItemBottomPopup
import com.sachidanand.storemanagement.presentation.navigation.ScreensObject
import com.sachidanand.storemanagement.presentation.screens.AddStoreItemScreen
import com.sachidanand.storemanagement.presentation.screens.DetailsStoreItemScreen
import com.sachidanand.storemanagement.presentation.screens.EditStoreItemScreen
import com.sachidanand.storemanagement.presentation.screens.HomeScreen
import com.sachidanand.storemanagement.presentation.theme.StoreManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoreManagementTheme {

                var navigationSelectedItem by remember {
                    mutableIntStateOf(0)
                }
                val navController = rememberNavController()
                val stores = rememberSaveable {
                    mutableListOf(
                        StoreItem(
                            1,
                            "Dolo 650",
                            "Paracetamol",
                            Priority.High,
                            1716462316220L,
                            10,
                            true
                        ),
                        StoreItem(2, "Alex syrup", "Syrup", Priority.Low, 340L, 10, false),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),
                        StoreItem(1, "Dolo 650", "Paracetamol", Priority.High, 340L, 10, true),

                        )
                }

                var showSheet by remember { mutableStateOf(false) }

                if (showSheet) {
                    val storeItem = addItemBottomPopup {
                        showSheet = false
                    }
                    stores.add(storeItem)
                }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            //Getting the list of bottom navigation items for our data class
                            BottomNavigationItem().getNavigationItems(LocalContext.current)
                                .forEachIndexed { index, navigationItem ->
                                    NavigationBarItem(
                                        label = {
                                            Text(navigationItem.label)
                                        },
                                        icon = {
                                            Icon(
                                                navigationItem.icon,
                                                contentDescription = navigationItem.label
                                            )
                                        },
                                        selected = index == navigationSelectedItem,
                                        onClick = {
                                            navigationSelectedItem = index
                                            navController.navigate(navigationItem.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                    )
                                }
                        }
                    },
                    /*floatingActionButton = {
                        val context = LocalContext.current
                        AnimatedAddButton(
                            value = UiText.StringResource(R.string.new_item),
                            onClick = {
                                //showSheet = true
                                //navController.navigate(Screens.AddStoreItem.route)
                                context.startActivity(
                                    Intent(
                                        context,
                                        AddStoreItemActivity::class.java
                                    )
                                )
                            }
                        )
                    }*/
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        //Map Screens based on their routes
                        composable(route = Screens.Home.route) {
                            HomeScreen(navController, modifier = Modifier, stores)
                        }
                        composable(Screens.Calender.route) {
                            //AddStoreItemScreen(navController)
                        }
                        composable(Screens.Settings.route) {
                            //SettingsScreen(navController)
                        }
                        composable(Screens.AddStoreItem.route) {
                            AddStoreItemScreen(navController = navController)
                        }
                        composable<ScreensObject.EditStoreItem> {
                            val storeItem = it.toRoute<ScreensObject.EditStoreItem>()
                            EditStoreItemScreen(
                                navController = navController,
                                storeItem.itemName,
                                storeItem.itemId,
                                storeItem.itemDescription,
                                storeItem.quantity,
                                storeItem.createdOn,
                                storeItem.inStore,
                                priority = storeItem.priority
                            )
                        }

                        composable<ScreensObject.DetailStoreItemScreen> {
                            val storeItem = it.toRoute<ScreensObject.DetailStoreItemScreen>()
                            DetailsStoreItemScreen(
                                navController = navController,
                                modifier = Modifier,
                                storeName = storeItem.itemName,
                                itemId = storeItem.itemId,
                                itemDescription = storeItem.itemDescription,
                                quantity = storeItem.quantity,
                                createdOn = storeItem.createdOn,
                                inStore = storeItem.inStore,
                                priority = storeItem.priority
                            )
                        }
                    }
                }
            }
        }
    }
}