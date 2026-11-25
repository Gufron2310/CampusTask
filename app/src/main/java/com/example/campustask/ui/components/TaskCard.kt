
package com.example.campustask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.campustask.data.Task

@Composable
fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = task.course,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Row {
                Text(
                    text = "Prioritas: ${task.priority}",
                    color = when (task.priority) {
                        "HIGH" -> Color(0xFFBA1A1A)
                        "MEDIUM" -> Color(0xFF9A6A16)
                        else -> Color(0xFF267044)
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Status: ${task.status}",
                    color = Color(0xFF245C45)
                )
            }
        }
    }
}
