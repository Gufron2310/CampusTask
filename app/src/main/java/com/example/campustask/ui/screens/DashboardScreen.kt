
package com.example.campustask.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campustask.data.Task
import com.example.campustask.ui.theme.GreenPrimary

@Composable
fun DashboardScreen(
    tasks: List<Task>,
    onAddTask: () -> Unit = {},
    onViewTasks: () -> Unit = {}
) {
    val totalTasks = tasks.size
    val pendingTasks = tasks.count { it.status == "TODO" }
    val inProgressTasks = tasks.count {
        it.status == "IN_PROGRESS"
    }
    val completedTasks = tasks.count { it.status == "DONE" }

    val upcomingTask = tasks
        .filter {
            it.status != "DONE" &&
                    it.deadline > System.currentTimeMillis()
        }
        .minByOrNull { it.deadline }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8F5)),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Column {
                Text(
                    text = "CAMPUS TASK",
                    color = GreenPrimary,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Halo, Mahasiswa! 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF202820)
                )

                Text(
                    text = "Kelola tugas kuliahmu hari ini.",
                    color = Color(0xFF707970),
                    fontSize = 14.sp
                )
            }
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Ringkasan Tugas",
                    style = MaterialTheme.typography.titleLarge
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryCard(
                        "Total Tugas",
                        totalTasks.toString(),
                        Color(0xFFE8F1EB),
                        Modifier.weight(1f)
                    )

                    SummaryCard(
                        "Belum Selesai",
                        pendingTasks.toString(),
                        Color(0xFFFFF3D9),
                        Modifier.weight(1f)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryCard(
                        "Dikerjakan",
                        inProgressTasks.toString(),
                        Color(0xFFE5EFF9),
                        Modifier.weight(1f)
                    )

                    SummaryCard(
                        "Selesai",
                        completedTasks.toString(),
                        Color(0xFFE5F3E9),
                        Modifier.weight(1f)
                    )
                }
            }
        }

        item {
            Column {
                Text(
                    text = "Tugas Terdekat",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (upcomingTask != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = upcomingTask.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = upcomingTask.course,
                                color = Color.Gray,
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Prioritas: ${upcomingTask.priority}",
                                color = if (upcomingTask.priority == "HIGH") {
                                    Color(0xFFBA1A1A)
                                } else {
                                    GreenPrimary
                                },
                                fontSize = 13.sp
                            )
                        }
                    }
                } else {
                    Text(
                        text = "Belum ada tugas mendatang.",
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            Button(
                onClick = onAddTask,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary
                )
            ) {
                Text("+ Tambah Tugas", fontSize = 16.sp)
            }
        }

        item {
            Button(
                onClick = onViewTasks,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8F1EB),
                    contentColor = GreenPrimary
                )
            ) {
                Text("Lihat Semua Tugas")
            }
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    count: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = count,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF245C45)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                color = Color(0xFF46534A)
            )
        }
    }
}
