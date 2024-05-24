package com.sachidanand.storemanagement.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachidanand.storemanagement.R
import com.sachidanand.storemanagement.domain.model.Priority
import com.sachidanand.storemanagement.domain.model.StoreItem

/**
 * Created by Sachidanand on 15-05-2024.
 */
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun StoreView(store: StoreItem, onClick: () -> Unit) {

    var isCompleted by remember {
        mutableStateOf(store.inStore)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_store_24),
                contentDescription = stringResource(id = R.string.your_Store),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),

            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                store?.itemName?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        store.itemDescription?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                    Text(
                        text = store.quantity.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Icon(
                        imageVector = if (store.inStore) Icons.Rounded.CheckCircle else Icons.Rounded.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskViewPreview() {
    StoreView(store = StoreItem(1, "Item 1", "New Item", Priority.Low, 330L, 10, true), onClick = {})
}