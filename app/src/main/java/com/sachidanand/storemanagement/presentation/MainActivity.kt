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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.presentation.navigation.Screens
import com.sachidanand.storemanagement.domain.model.BottomNavigationItem
import com.sachidanand.storemanagement.presentation.components.AddItemBottomPopup
import com.sachidanand.storemanagement.presentation.components.AnimatedAddButton
import com.sachidanand.storemanagement.presentation.screens.HomeScreen
import com.sachidanand.storemanagement.presentation.theme.StoreManagementTheme
import com.sachidanand.storemanagement.utils.UiText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoreManagementTheme {

                var navigationSelectedItem by remember {
                    mutableIntStateOf(0)
                }
                val navController = rememberNavController()

                var showSheet by remember { mutableStateOf(false) }

                if (showSheet) {
                    AddItemBottomPopup {
                        showSheet = false
                    }
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
                    floatingActionButton = {
                        AnimatedAddButton(
                            value = UiText.StringResource(R.string.new_item),
                            onClick = {
                                showSheet = true
                            }
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        //Map Screens based on their routes
                        composable(Screens.Home.route) {
                            HomeScreen(navController, modifier = Modifier)
                        }
                        composable(Screens.Calender.route) {
                            //CalenderScreen(navController)
                        }
                        composable(Screens.Settings.route) {
                            //SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}