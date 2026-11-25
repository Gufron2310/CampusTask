
package com.example.campustask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val course: String,

    val description: String,

    val deadline: Long,

    val priority: String = "MEDIUM",

    val status: String = "TODO",

    val createdAt: Long = System.currentTimeMillis()
)
