package com.sachidanand.storemanagement.presentation.screens

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

/**
 * Created by Sachidanand on 14-05-2024.
 */
@Composable
fun TaskEditorScreen(navController: NavController) {
    Surface {
        ConstraintLayout {
            Text(text = "Store Editor")
        }
    }

}

