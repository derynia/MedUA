package com.medua.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.medua.R
import com.medua.ui.theme.*

sealed class HomeScreenMenu(
    val route: String,
    @DrawableRes val iconId: Int,
    val backgroundColor: Color,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    object Analyzes :
        HomeScreenMenu("analyzes", R.drawable.analyzes, Pink, R.string.analyzes, R.string.analyzes_desc)

    object Diagnosis :
        HomeScreenMenu(
            "diagnosis",
            R.drawable.diagnosis,
            LightGreen,
            R.string.diagnosis,
            R.string.diagnosis_desc
        )

    object DoctorsVisit :
        HomeScreenMenu(
            "doctorsvisit",
            R.drawable.doctors_visit,
            LightYellow,
            R.string.doctors_visit,
            R.string.diagnosis_desc
        )

    object Medicines :
        HomeScreenMenu("medicines", R.drawable.medicines, LightGrey, R.string.medicines, R.string.medicines_desc)

    object Allergy :
        HomeScreenMenu(
            "allergies",
            R.drawable.allergies,
            VeryLightGreen,
            R.string.allergy,
            R.string.allergy_desc
        )

    object Vaccinations :
        HomeScreenMenu(
            "vaccinations",
            R.drawable.vaccines,
            VeryLightPink,
            R.string.vaccine,
            R.string.vaccination_desc
        )
}

val homeItems = listOf(
    HomeScreenMenu.Analyzes,
    HomeScreenMenu.Diagnosis,
    HomeScreenMenu.DoctorsVisit,
    HomeScreenMenu.Medicines,
    HomeScreenMenu.Allergy,
    HomeScreenMenu.Vaccinations
)
