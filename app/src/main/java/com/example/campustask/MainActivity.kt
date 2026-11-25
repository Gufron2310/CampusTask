
package com.example.campustask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.campustask.navigation.AppNavigation
import com.example.campustask.ui.theme.CampusTaskTheme
import com.example.campustask.viewmodel.TaskViewModel
import com.example.campustask.viewmodel.TaskViewModelProvider

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val provider = TaskViewModelProvider(applicationContext)

        setContent {
            CampusTaskTheme {

                val taskViewModel: TaskViewModel = viewModel(
                    factory = provider.factory()
                )

                AppNavigation(
                    taskViewModel = taskViewModel
                )
            }
        }
    }
}

