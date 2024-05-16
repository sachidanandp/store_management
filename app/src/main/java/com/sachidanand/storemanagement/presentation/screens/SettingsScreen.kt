package com.zaki.pintask.presentation.screens

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

/**
 * Created by Zaki on 05-10-2023.
 */
@Composable
fun SettingsScreen(navController: NavController) {
    Surface {
        ConstraintLayout {
            Text(text = "Settings")
        }
    }

}

