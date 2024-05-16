package com.sachidanand.storemanagement.presentation.navigation

/**
 * Created by Sachidanand on 13-05-2024.
 */
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Calender : Screens("calender")
    object Settings : Screens("settings")
    object TaskEditor : Screens("task_editor")
    object About : Screens("about")
}
