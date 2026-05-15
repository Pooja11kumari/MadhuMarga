package com.example.madhu_marga.navigation

sealed class AppScreen(val route: String) {
    data object Login : AppScreen("login")
    data object Dashboard : AppScreen("dashboard")
    data object HiveRegister : AppScreen("hive_register")
    data object InspectionLog : AppScreen("inspection_log")
    data object HarvestTracker : AppScreen("harvest_tracker")
    data object FloraCalendar : AppScreen("flora_calendar")
}
