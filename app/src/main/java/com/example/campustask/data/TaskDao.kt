
package com.example.campustask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // Mengambil seluruh tugas
    @Query("SELECT * FROM tasks ORDER BY deadline ASC")
    fun getAllTasks(): Flow<List<Task>>

    // Mengambil tugas berdasarkan ID
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<Task?>

    // Menambahkan tugas baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    // Memperbarui tugas
    @Update
    suspend fun updateTask(task: Task)

    // Menghapus tugas
    @Delete
    suspend fun deleteTask(task: Task)

    // Menghapus seluruh tugas
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}
