
package com.example.campustask.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.campustask.data.TaskDatabase
import com.example.campustask.data.TaskRepository

class TaskViewModelProvider(
    context: Context
) {

    private val database =
        TaskDatabase.getDatabase(context)

    private val repository =
        TaskRepository(database.taskDao())

    fun factory(): ViewModelProvider.Factory {
        return TaskViewModelFactory(repository)
    }
}
