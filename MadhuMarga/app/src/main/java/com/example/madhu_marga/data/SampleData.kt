package com.example.madhu_marga.data

import com.example.madhu_marga.model.Hive

object SampleData {
    val hives = listOf(
        Hive(id = "H-101", name = "Golden Hive", location = "North Field", status = "Healthy"),
        Hive(id = "H-102", name = "Neem Garden", location = "Canal Side", status = "Observe"),
        Hive(id = "H-103", name = "Sunrise Box", location = "Mango Farm", status = "Active")
    )

    val floraCalendar = mapOf(
        "March" to listOf("Mango Blossom", "Sunflower"),
        "April" to listOf("Neem", "Coconut"),
        "May" to listOf("Drumstick Tree", "Sesame")
    )
}
