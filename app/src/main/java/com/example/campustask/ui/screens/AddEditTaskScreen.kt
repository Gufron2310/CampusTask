
package com.example.campustask.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.campustask.data.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    existingTask: Task? = null,
    onSave: (Task) -> Unit,
    onCancel: () -> Unit = {}
) {
    var title by remember(existingTask?.id) {
        mutableStateOf(existingTask?.title ?: "")
    }

    var course by remember(existingTask?.id) {
        mutableStateOf(existingTask?.course ?: "")
    }

    var description by remember(existingTask?.id) {
        mutableStateOf(existingTask?.description ?: "")
    }

    var priority by remember(existingTask?.id) {
        mutableStateOf(existingTask?.priority ?: "MEDIUM")
    }

    var status by remember(existingTask?.id) {
        mutableStateOf(existingTask?.status ?: "TODO")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = existingTask?.deadline
    )

    var priorityExpanded by remember {
        mutableStateOf(false)
    }

    var statusExpanded by remember {
        mutableStateOf(false)
    }

    val selectedDate = datePickerState.selectedDateMillis

    val dateText = selectedDate?.let {
        SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        ).format(Date(it))
    } ?: ""

    val isFormValid =
        title.isNotBlank() &&
                course.isNotBlank() &&
                selectedDate != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Text(
            text = if (existingTask == null) {
                "Tambah Tugas"
            } else {
                "Edit Tugas"
            }
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul Tugas") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Mata Kuliah") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Deskripsi Tugas") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        OutlinedButton(
            onClick = {
                showDatePicker = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (dateText.isBlank()) {
                    "Pilih Deadline"
                } else {
                    "Deadline: $dateText"
                }
            )
        }

        ExposedDropdownMenuBox(
            expanded = priorityExpanded,
            onExpandedChange = {
                priorityExpanded = !priorityExpanded
            }
        ) {
            OutlinedTextField(
                value = priority,
                onValueChange = {},
                readOnly = true,
                label = { Text("Prioritas") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = priorityExpanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = priorityExpanded,
                onDismissRequest = {
                    priorityExpanded = false
                }
            ) {
                listOf("LOW", "MEDIUM", "HIGH").forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            priority = it
                            priorityExpanded = false
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = statusExpanded,
            onExpandedChange = {
                statusExpanded = !statusExpanded
            }
        ) {
            OutlinedTextField(
                value = status,
                onValueChange = {},
                readOnly = true,
                label = { Text("Status") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = statusExpanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = statusExpanded,
                onDismissRequest = {
                    statusExpanded = false
                }
            ) {
                listOf(
                    "TODO",
                    "IN_PROGRESS",
                    "DONE"
                ).forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            status = it
                            statusExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val deadlineValue = selectedDate ?: return@Button

                val task = Task(
                    id = existingTask?.id ?: 0,
                    title = title.trim(),
                    course = course.trim(),
                    description = description.trim(),
                    deadline = deadlineValue,
                    priority = priority,
                    status = status,
                    createdAt = existingTask?.createdAt
                        ?: System.currentTimeMillis()
                )

                onSave(task)
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (existingTask == null) {
                    "Simpan Tugas"
                } else {
                    "Simpan Perubahan"
                }
            )
        }

        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Batal")
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Pilih")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}
