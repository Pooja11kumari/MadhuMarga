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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhu_marga.model.Hive
import com.example.madhu_marga.ui.components.AppCard
import com.example.madhu_marga.ui.components.InfoCard
import com.example.madhu_marga.ui.components.ScreenTopBar
import com.example.madhu_marga.ui.components.SectionHeader
import com.example.madhu_marga.ui.theme.HoneySuccess
import com.example.madhu_marga.ui.theme.HoneyWarning

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectionLogScreen(
    hives: List<Hive>,
    onBack: () -> Unit
) {
    val hiveOptions = hives.map { it.name }
    val activityLevels = listOf("High", "Medium", "Low")

    var selectedHive by remember { mutableStateOf(hiveOptions.firstOrNull().orEmpty()) }
    var queenPresent by remember { mutableStateOf(true) }
    var activityLevel by remember { mutableStateOf("High") }
    var pestsSeen by remember { mutableStateOf(false) }
    var honeyFlow by remember { mutableFloatStateOf(70f) }
    var resultMessage by remember { mutableStateOf("Analyze the hive to see a suggestion.") }

    var hiveExpanded by remember { mutableStateOf(false) }
    var activityExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTopBar(title = "Inspection Log", onBack = onBack)

        AppCard {
            SectionHeader(
                title = "Inspect hive",
                subtitle = "Use simple checks to review hive health"
            )

            ExposedDropdownMenuBox(
                expanded = hiveExpanded,
                onExpandedChange = { hiveExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedHive,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Hive") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = hiveExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                DropdownMenu(
                    expanded = hiveExpanded,
                    onDismissRequest = { hiveExpanded = false }
                ) {
                    hiveOptions.forEach { hiveName ->
                        DropdownMenuItem(
                            text = { Text(hiveName) },
                            onClick = {
                                selectedHive = hiveName
                                hiveExpanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Queen Present", style = MaterialTheme.typography.bodyLarge)
                Switch(checked = queenPresent, onCheckedChange = { queenPresent = it })
            }

            ExposedDropdownMenuBox(
                expanded = activityExpanded,
                onExpandedChange = { activityExpanded = it }
            ) {
                OutlinedTextField(
                    value = activityLevel,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Activity Level") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = activityExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                DropdownMenu(
                    expanded = activityExpanded,
                    onDismissRequest = { activityExpanded = false }
                ) {
                    activityLevels.forEach { level ->
                        DropdownMenuItem(
                            text = { Text(level) },
                            onClick = {
                                activityLevel = level
                                activityExpanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Pests Seen", style = MaterialTheme.typography.bodyLarge)
                Switch(checked = pestsSeen, onCheckedChange = { pestsSeen = it })
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Honey Flow: ${honeyFlow.toInt()}")
                Slider(
                    value = honeyFlow,
                    onValueChange = { honeyFlow = it },
                    valueRange = 0f..100f
                )
            }

            Button(
                onClick = {
                    resultMessage = when {
                        !queenPresent -> "Queen missing. Inspect hive immediately."
                        activityLevel == "Low" -> "Low activity detected. Check hive health."
                        pestsSeen -> "Pests detected. Clean hive."
                        honeyFlow < 30f -> "Low honey flow. Wait before harvest."
                        else -> "Hive healthy."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Analyze Hive")
            }
        }

        InfoCard(
            message = resultMessage,
            accent = if (resultMessage == "Hive healthy.") HoneySuccess else HoneyWarning
        )
    }
}
