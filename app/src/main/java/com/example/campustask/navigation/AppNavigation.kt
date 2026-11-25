package com.example.campustask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.campustask.ui.screens.AddEditTaskScreen
import com.example.campustask.ui.screens.DashboardScreen
import com.example.campustask.ui.screens.TaskDetailScreen
import com.example.campustask.ui.screens.TaskListScreen
import com.example.campustask.viewmodel.TaskViewModel

object AppRoutes {
    const val DASHBOARD = "dashboard"
    const val TASK_LIST = "task_list"
    const val ADD_TASK = "add_task"
    const val TASK_DETAIL = "task_detail/{taskId}"
    const val EDIT_TASK = "edit_task/{taskId}"

    fun taskDetail(taskId: Int) = "task_detail/$taskId"

    fun editTask(taskId: Int) = "edit_task/$taskId"
}

@Composable
fun AppNavigation(taskViewModel: TaskViewModel) {

    val navController = rememberNavController()

    val tasks by taskViewModel.tasks.collectAsState()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.DASHBOARD
    ) {

        // DASHBOARD
        composable(AppRoutes.DASHBOARD) {
            DashboardScreen(
                tasks = tasks,
                onAddTask = {
                    navController.navigate(AppRoutes.ADD_TASK)
                },
                onViewTasks = {
                    navController.navigate(AppRoutes.TASK_LIST)
                }
            )
        }

        // TASK LIST
        composable(AppRoutes.TASK_LIST) {
            TaskListScreen(
                tasks = tasks,
                onTaskClick = { taskId ->
                    navController.navigate(
                        AppRoutes.taskDetail(taskId)
                    )
                },
                onAddTask = {
                    navController.navigate(AppRoutes.ADD_TASK)
                }
            )
        }

        // ADD TASK
        composable(AppRoutes.ADD_TASK) {
            AddEditTaskScreen(
                onSave = { task ->
                    taskViewModel.insertTask(task)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        // TASK DETAIL
        composable(
            route = AppRoutes.TASK_DETAIL,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments
                ?.getInt("taskId")

            val selectedTask = tasks.find {
                it.id == taskId
            }

            if (selectedTask != null) {
                TaskDetailScreen(
                    task = selectedTask,

                    // EDIT TASK
                    onEdit = {
                        navController.navigate(
                            AppRoutes.editTask(selectedTask.id)
                        )
                    },

                    // DELETE TASK
                    onDelete = {
                        taskViewModel.deleteTask(selectedTask)
                        navController.popBackStack()
                    },

                    // BACK
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tugas tidak ditemukan")
                }
            }
        }

        // EDIT TASK
        composable(
            route = AppRoutes.EDIT_TASK,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments
                ?.getInt("taskId")

            val selectedTask = tasks.find {
                it.id == taskId
            }

            if (selectedTask != null) {
                AddEditTaskScreen(
                    existingTask = selectedTask,

                    onSave = { updatedTask ->
                        taskViewModel.updateTask(updatedTask)
                        navController.popBackStack()
                    },

                    onCancel = {
                        navController.popBackStack()
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tugas tidak ditemukan")
                }
            }
        }
    }
}