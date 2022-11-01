package com.medua

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medua.presentation.doctors.DoctorsScreen
import com.medua.presentation.home.HomeScreen
import com.medua.presentation.navigation.Screen
import com.medua.presentation.navigation.navItems
import com.medua.presentation.pills.PillsScreen
import com.medua.presentation.profile.ProfileScreen
import com.medua.ui.theme.MedUATheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedUATheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            navItems.forEach { screen ->
                                val selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(if (selected) screen.selectedIconId else screen.iconId),
                                            contentDescription = stringResource(screen.resourceId)
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = selected,
                                    colors = getNavItemColors(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.secondary
                                    ),
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) { HomeScreen() }
                        composable(Screen.Doctors.route) { DoctorsScreen() }
                        composable(Screen.Pills.route) { PillsScreen() }
                        composable(Screen.Profile.route) { ProfileScreen() }
                    }
                }
            }
        }
    }

    @Composable
    private fun getNavItemColors(
        colorSelected: Color,
        indicatorColor: Color
    ): NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = colorSelected,
        selectedTextColor = colorSelected,
        indicatorColor = indicatorColor
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MedUATheme {
//        Greeting("Android")
    }
}