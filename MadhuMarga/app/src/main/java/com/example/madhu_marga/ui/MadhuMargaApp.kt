package com.example.madhu_marga.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.madhu_marga.data.SampleData
import com.example.madhu_marga.model.Hive
import com.example.madhu_marga.navigation.AppScreen
import com.example.madhu_marga.ui.screens.DashboardScreen
import com.example.madhu_marga.ui.screens.FloraCalendarScreen
import com.example.madhu_marga.ui.screens.HarvestTrackerScreen
import com.example.madhu_marga.ui.screens.HiveRegisterScreen
import com.example.madhu_marga.ui.screens.InspectionLogScreen
import com.example.madhu_marga.ui.screens.LoginScreen

@Composable
fun MadhuMargaApp() {
    val navController = rememberNavController()

    // Local UI state keeps the project beginner-friendly while still allowing additions at runtime.
    val hiveList = remember { mutableStateListOf<Hive>().apply { addAll(SampleData.hives) } }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppNavHost(
            navController = navController,
            hives = hiveList,
            onSaveHive = { hive -> hiveList.add(hive) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    hives: List<Hive>,
    onSaveHive: (Hive) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route,
        modifier = modifier
    ) {
        composable(AppScreen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(AppScreen.Dashboard.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppScreen.Dashboard.route) {
            DashboardScreen(
                onRegisterHiveClick = { navController.navigate(AppScreen.HiveRegister.route) },
                onInspectionLogClick = { navController.navigate(AppScreen.InspectionLog.route) },
                onHarvestTrackerClick = { navController.navigate(AppScreen.HarvestTracker.route) },
                onFloraCalendarClick = { navController.navigate(AppScreen.FloraCalendar.route) }
            )
        }

        composable(AppScreen.HiveRegister.route) {
            HiveRegisterScreen(
                hives = hives,
                onSaveHive = onSaveHive,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.InspectionLog.route) {
            InspectionLogScreen(
                hives = hives,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.HarvestTracker.route) {
            HarvestTrackerScreen(
                hives = hives,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.FloraCalendar.route) {
            FloraCalendarScreen(onBack = { navController.popBackStack() })
        }
    }
}
