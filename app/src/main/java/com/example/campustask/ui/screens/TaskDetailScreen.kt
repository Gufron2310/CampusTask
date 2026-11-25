package com.example.campustask.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.campustask.data.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskDetailScreen(
    task: Task,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    onBack: () -> Unit = {}
) {

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    val deadlineText = SimpleDateFormat(
        "dd MMMM yyyy, HH:mm",
        Locale("id", "ID")
    ).format(Date(task.deadline))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Detail Tugas",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = task.title,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Mata Kuliah: ${task.course}"
        )

        Text(
            text = "Deskripsi:"
        )

        Text(
            text = task.description.ifBlank {
                "Tidak ada deskripsi."
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Deadline: $deadlineText"
        )

        Text(
            text = "Prioritas: ${task.priority}"
        )

        Text(
            text = "Status: ${task.status}"
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onEdit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Tugas")
        }

        OutlinedButton(
            onClick = {
                showDeleteDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hapus Tugas")
        }

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali")
        }
    }

    // DIALOG KONFIRMASI HAPUS
    if (showDeleteDialog) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text("Konfirmasi Hapus")
            },

            text = {
                Text(
                    "Apakah Anda yakin ingin menghapus " +
                            "tugas \"${task.title}\"? " +
                            "Tugas yang dihapus tidak dapat dikembalikan."
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDelete()
                    }
                ) {
                    Text("Ya, Hapus")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}