package com.medua.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.medua.R
import com.medua.ui.theme.*

sealed class HomeScreen(
    @DrawableRes val iconId: Int,
    val backgroundColor: Color,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    object Analyzes :
        HomeScreen(R.drawable.analyzes, Pink, R.string.analyzes, R.string.analyzes_desc)

    object Diagnosis :
        HomeScreen(R.drawable.diagnosis, LightGreen, R.string.diagnosis, R.string.diagnosis_desc)

    object DoctorsVisit :
        HomeScreen(R.drawable.doctors_visit, LightYellow, R.string.doctors_visit, R.string.diagnosis_desc)

    object Medicines :
        HomeScreen(R.drawable.medicines, LightGrey, R.string.medicines, R.string.medicines_desc)

    object Allergy :
        HomeScreen(R.drawable.allergies, VeryLightGreen, R.string.allergy, R.string.allergy_desc)

    object Vaccinations :
        HomeScreen(R.drawable.vaccines, VeryLightPink, R.string.vaccines, R.string.vaccination_desc)
}

val homeItems = listOf(
    HomeScreen.Analyzes,
    HomeScreen.Diagnosis,
    HomeScreen.DoctorsVisit,
    HomeScreen.Medicines,
    HomeScreen.Allergy,
    HomeScreen.Vaccinations
)
