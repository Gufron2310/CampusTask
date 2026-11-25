
package com.example.campustask.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.campustask.data.Task
import com.example.campustask.ui.components.TaskCard
import com.example.campustask.ui.theme.GreenPrimary

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Int) -> Unit = {},
    onAddTask: () -> Unit = {}
) {
    var selectedFilter by remember {
        mutableStateOf("ALL")
    }

    val filteredTasks = when (selectedFilter) {
        "TODO" -> tasks.filter { it.status == "TODO" }
        "IN_PROGRESS" -> tasks.filter {
            it.status == "IN_PROGRESS"
        }
        "DONE" -> tasks.filter { it.status == "DONE" }
        else -> tasks
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Daftar Tugas",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Kelola semua tugas kuliahmu.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filters = listOf(
                "ALL" to "Semua",
                "TODO" to "Belum",
                "IN_PROGRESS" to "Dikerjakan",
                "DONE" to "Selesai"
            )

            filters.forEach { (value, label) ->
                FilterChip(
                    selected = selectedFilter == value,
                    onClick = {
                        selectedFilter = value
                    },
                    label = {
                        Text(label)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${filteredTasks.size} Tugas",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredTasks.isEmpty()) {
            Text(
                text = "Belum ada tugas pada kategori ini.",
                modifier = Modifier.padding(vertical = 24.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = filteredTasks,
                    key = { it.id }
                ) { task ->
                    TaskCard(
                        task = task,
                        onClick = {
                            onTaskClick(task.id)
                        }
                    )
                }
            }
        }

        Button(
            onClick = onAddTask,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Tambah Tugas")
        }
    }
}
