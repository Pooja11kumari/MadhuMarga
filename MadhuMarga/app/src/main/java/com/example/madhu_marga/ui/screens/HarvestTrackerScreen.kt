package com.example.madhu_marga.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhu_marga.model.HarvestEntry
import com.example.madhu_marga.model.Hive
import com.example.madhu_marga.ui.components.AppCard
import com.example.madhu_marga.ui.components.ScreenTopBar
import com.example.madhu_marga.ui.components.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarvestTrackerScreen(
    hives: List<Hive>,
    onBack: () -> Unit
) {
    val hiveOptions = hives.map { it.name }
    val harvestEntries = remember { mutableStateListOf<HarvestEntry>() }

    var selectedHive by remember { mutableStateOf(hiveOptions.firstOrNull().orEmpty()) }
    var honeyQuantity by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("2026") }
    var expanded by remember { mutableStateOf(false) }

    val lastYear = 20f
    val currentYear = 28f
    val growth = (((currentYear - lastYear) / lastYear) * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTopBar(title = "Harvest Tracker", onBack = onBack)

        AppCard {
            SectionHeader(
                title = "Save harvest",
                subtitle = "Track honey output from each hive"
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedHive,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Hive") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    hiveOptions.forEach { hiveName ->
                        DropdownMenuItem(
                            text = { Text(hiveName) },
                            onClick = {
                                selectedHive = hiveName
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = honeyQuantity,
                onValueChange = { honeyQuantity = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Honey Quantity (kg)") },
                singleLine = true
            )

            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Year") },
                singleLine = true
            )

            Button(
                onClick = {
                    if (selectedHive.isNotBlank() && honeyQuantity.isNotBlank() && year.isNotBlank()) {
                        harvestEntries.add(
                            HarvestEntry(
                                hiveId = selectedHive,
                                quantityKg = honeyQuantity,
                                year = year
                            )
                        )
                        honeyQuantity = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save")
            }
        }

        AppCard {
            SectionHeader(
                title = "Yearly comparison",
                subtitle = "Simple static comparison for this MVP"
            )
            Text(text = "2025 -> 20kg")
            Text(text = "2026 -> 28kg")
            Text(text = "Growth: $growth%")
        }

        if (harvestEntries.isNotEmpty()) {
            AppCard {
                SectionHeader(
                    title = "Saved entries",
                    subtitle = "Temporary local entries for this session"
                )
                harvestEntries.forEach { entry ->
                    Text(text = "${entry.year} | ${entry.hiveId} | ${entry.quantityKg} kg")
                }
            }
        }
    }
}
