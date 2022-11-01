package com.medua.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.medua.R

sealed class Screen(
    val route: String,
    @DrawableRes val iconId: Int,
    @DrawableRes val selectedIconId: Int,
    @StringRes val resourceId: Int
) {
    object Home :
        Screen("home", R.drawable.home_unselected, R.drawable.home_selected, R.string.home)

    object Doctors : Screen(
        "doctors",
        R.drawable.doctors_unselected,
        R.drawable.doctors_selected,
        R.string.doctors
    )

    object Pills :
        Screen("pills", R.drawable.pills_unselected, R.drawable.pills_selected, R.string.pills)

    object Profile : Screen(
        "profile",
        R.drawable.profile_unselected,
        R.drawable.profile_selected,
        R.string.profile
    )
}

val navItems = listOf(
    Screen.Home,
    Screen.Doctors,
    Screen.Pills,
    Screen.Profile
)