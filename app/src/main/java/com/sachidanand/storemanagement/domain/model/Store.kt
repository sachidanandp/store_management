package com.sachidanand.storemanagement.domain.model


data class StoreItem(
    val itemId: Int,
    val itemName: String,
    val itemDescription: String,
    val priority: Priority,
    val createdOn: Long,
    val quantity: Int,
    val inStore: Boolean = false
)

enum class Priority{
    High, Medium, Low
}
