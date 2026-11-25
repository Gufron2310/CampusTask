
package com.example.campustask.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {

    // Mengambil seluruh tugas
    val allTasks: Flow<List<Task>> =
        taskDao.getAllTasks()

    // Mengambil tugas berdasarkan ID
    fun getTaskById(taskId: Int): Flow<Task?> {
        return taskDao.getTaskById(taskId)
    }

    // Menambahkan tugas
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    // Memperbarui tugas
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    // Menghapus tugas
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    // Menghapus seluruh tugas
    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }
}
