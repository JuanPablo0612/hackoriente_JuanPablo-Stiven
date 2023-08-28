package com.conectaedu.android.ui.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
    object AreaRegistration: Screen("area_registration")
    object Area: Screen("area")
    object Course: Screen("course")
    object StudyGroup: Screen("studyGroup")
}