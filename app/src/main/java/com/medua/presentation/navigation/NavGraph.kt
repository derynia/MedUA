package com.medua.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medua.R
import com.medua.presentation.doctors.DoctorsScreen
import com.medua.presentation.home.AdditionalHomeScreen
import com.medua.presentation.home.HomeScreen
import com.medua.presentation.pills.PillsScreen
import com.medua.presentation.pills.PillsViewModel
import com.medua.presentation.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController, innerPadding: PaddingValues, viewModel: PillsViewModel) {
    NavHost(
        navController,
        startDestination = Screen.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Doctors.route) { DoctorsScreen() }
        composable(Screen.Pills.route) { PillsScreen(viewModel) }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(HomeScreenMenu.DoctorsVisit.route) {
            AdditionalHomeScreen(
                title = R.string.doctors_visit,
                iconId = R.drawable.doctors_visits_oops,
                contentDescription = R.string.doctors_visit,
                text = R.string.doctors_visit_desc,
                buttonCaption = R.string.add_doctors_visit,
                navController = navController,
                onClick = { }
            )
        }
        composable(HomeScreenMenu.Diagnosis.route) {
            AdditionalHomeScreen(
                title = R.string.diagnosis,
                iconId = R.drawable.diagnosis_oops,
                contentDescription = R.string.diagnosis,
                text = R.string.diagnosis_desc,
                buttonCaption = R.string.add_diagnoses,
                navController = navController,
                onClick = { }
            )
        }
        composable(HomeScreenMenu.Analyzes.route) {
            AdditionalHomeScreen(
                title = R.string.analyzes,
                iconId = R.drawable.analyzes_oops,
                contentDescription = R.string.analyzes,
                text = R.string.analyzes_desc,
                buttonCaption = R.string.add_analyzes,
                navController = navController,
                onClick = { }
            )
        }
        composable(HomeScreenMenu.Medicines.route) {
            AdditionalHomeScreen(
                title = R.string.medicines,
                iconId = R.drawable.medicines_oops,
                contentDescription = R.string.medicines,
                text = R.string.medicines_desc,
                buttonCaption = R.string.add_medicines,
                navController = navController,
                onClick = { }
            )
        }
        composable(HomeScreenMenu.Vaccinations.route) {
            AdditionalHomeScreen(
                title = R.string.vaccines,
                iconId = R.drawable.vaccines_oops,
                contentDescription = R.string.vaccines,
                text = R.string.vaccination_desc,
                buttonCaption = R.string.add_vaccines,
                navController = navController,
                onClick = { }
            )
        }
        composable(HomeScreenMenu.Allergy.route) {
            AdditionalHomeScreen(
                title = R.string.allergies,
                iconId = R.drawable.allergies_oops,
                contentDescription = R.string.allergies,
                text = R.string.allergy_desc,
                buttonCaption = R.string.add_allergies,
                navController = navController,
                onClick = { }
            )
        }
    }
}