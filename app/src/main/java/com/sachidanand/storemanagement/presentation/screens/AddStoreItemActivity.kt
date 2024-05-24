package com.sachidanand.storemanagement.presentation.screens

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.sachidanand.storemanagement.presentation.theme.StoreManagementTheme

class AddStoreItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                Box {
                    Button(onClick = { }) {
                        Text(text = "Back button")
                    }
                    Text(text = "Hello")
                }
            }

    }
}