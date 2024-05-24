package com.sachidanand.storemanagement.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class StoreItem(
    val itemId: Int = 0,
    val itemName: String? = null,
    val itemDescription: String? = null,
    val priority: Priority = Priority.Medium,
    val createdOn: Long = 0,
    val quantity: Int = 0,
    val inStore: Boolean = false
): Parcelable

enum class Priority{
    High, Medium, Low
}
