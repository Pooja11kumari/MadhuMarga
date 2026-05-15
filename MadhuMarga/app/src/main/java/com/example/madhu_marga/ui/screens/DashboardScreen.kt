package com.example.madhu_marga.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhu_marga.ui.components.NavigationCard
import com.example.madhu_marga.ui.components.ScreenTopBar
import com.example.madhu_marga.ui.components.SectionHeader
import com.example.madhu_marga.ui.components.StatCard

@Composable
fun DashboardScreen(
    onRegisterHiveClick: () -> Unit,
    onInspectionLogClick: () -> Unit,
    onHarvestTrackerClick: () -> Unit,
    onFloraCalendarClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTopBar(title = "Dashboard")
        SectionHeader(
            title = "Bee farm overview",
            subtitle = "Quick access to hives, inspections, harvests and flowering seasons"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                StatCard(label = "Total Hives", value = "12")
            }
            Column(modifier = Modifier.weight(1f)) {
                StatCard(label = "Healthy Hives", value = "9")
            }
        }

        StatCard(label = "Active Alerts", value = "3")

        Text(text = "Manage", modifier = Modifier.padding(top = 8.dp))

        NavigationCard(
            title = "Register Hive",
            subtitle = "Add new hives and check existing boxes quickly.",
            buttonText = "Open",
            onClick = onRegisterHiveClick
        )
        NavigationCard(
            title = "Inspection Log",
            subtitle = "Record quick hive observations and basic health checks.",
            buttonText = "Open",
            onClick = onInspectionLogClick
        )
        NavigationCard(
            title = "Harvest Tracker",
            subtitle = "Save honey harvest details and compare yearly output.",
            buttonText = "Open",
            onClick = onHarvestTrackerClick
        )
        NavigationCard(
            title = "Flora Calendar",
            subtitle = "See seasonal flowering plants that support honey flow.",
            buttonText = "Open",
            onClick = onFloraCalendarClick
        )
    }
}
