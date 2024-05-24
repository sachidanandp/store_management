package com.sachidanand.storemanagement.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Created by Sachidanand on 13-05-2024.
 */
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Calender : Screens("calender")
    object Settings : Screens("settings")
    object TaskEditor : Screens("task_editor")
    object About : Screens("about")
    object AddStoreItem : Screens("add_store_item")
}


sealed class ScreensObject {
    @Serializable
    data class EditStoreItem(
        val itemId: Int = 0,
        val itemName: String? = null,
        val itemDescription: String? = null,
        val priority: String? = null,
        val createdOn: Long = 0,
        val quantity: Int = 0,
        val inStore: Boolean = false
    )

    @Serializable
    data class DetailStoreItemScreen(
        val itemId: Int = 0,
        val itemName: String? = null,
        val itemDescription: String? = null,
        val priority: String? = null,
        val createdOn: Long = 0,
        val quantity: Int = 0,
        val inStore: Boolean = false
    )

}
