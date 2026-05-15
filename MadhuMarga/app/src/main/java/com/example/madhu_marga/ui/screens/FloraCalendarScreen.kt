package com.example.madhu_marga.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhu_marga.data.SampleData
import com.example.madhu_marga.ui.components.AppCard
import com.example.madhu_marga.ui.components.ScreenTopBar
import com.example.madhu_marga.ui.components.SectionHeader

@Composable
fun FloraCalendarScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTopBar(title = "Flora Calendar", onBack = onBack)
        SectionHeader(
            title = "Flowering guide",
            subtitle = "Static monthly reference for nearby nectar sources"
        )

        SampleData.floraCalendar.forEach { (month, flowers) ->
            AppCard {
                Text(text = month)
                flowers.forEach { flower ->
                    Text(text = "- $flower")
                }
            }
        }
    }
}
