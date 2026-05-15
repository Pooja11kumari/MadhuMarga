package com.example.madhu_marga.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhu_marga.model.Hive
import com.example.madhu_marga.ui.components.AppCard
import com.example.madhu_marga.ui.components.ScreenTopBar
import com.example.madhu_marga.ui.components.SectionHeader
import com.example.madhu_marga.ui.components.StatusBadge

@Composable
fun HiveRegisterScreen(
    hives: List<Hive>,
    onSaveHive: (Hive) -> Unit,
    onBack: () -> Unit
) {
    var hiveId by remember { mutableStateOf("") }
    var hiveName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTopBar(title = "Hive Register", onBack = onBack)

        AppCard {
            SectionHeader(
                title = "Register new hive",
                subtitle = "Add a hive using simple details only"
            )

            OutlinedTextField(
                value = hiveId,
                onValueChange = { hiveId = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Hive ID") },
                singleLine = true
            )
            OutlinedTextField(
                value = hiveName,
                onValueChange = { hiveName = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Hive Name") },
                singleLine = true
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Location") },
                singleLine = true
            )

            Button(
                onClick = {
                    if (hiveId.isNotBlank() && hiveName.isNotBlank() && location.isNotBlank()) {
                        onSaveHive(
                            Hive(
                                id = hiveId,
                                name = hiveName,
                                location = location,
                                status = "Active"
                            )
                        )
                        hiveId = ""
                        hiveName = ""
                        location = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save")
            }
        }

        SectionHeader(
            title = "Hive list",
            subtitle = "Dummy and newly added hives are shown below"
        )

        hives.forEach { hive ->
            AppCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(text = hive.id, style = MaterialTheme.typography.titleMedium)
                        Text(text = hive.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = hive.location, style = MaterialTheme.typography.bodyMedium)
                    }
                    StatusBadge(status = hive.status)
                }
            }
        }
    }
}
