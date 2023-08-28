package com.conectaedu.android.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.conectaedu.android.ui.area.AreaScreen
import com.conectaedu.android.ui.arearegistration.AreaRegistrationScreen
import com.conectaedu.android.ui.course.CourseScreen
import com.conectaedu.android.ui.home.HomeScreen
import com.conectaedu.android.ui.login.LoginScreen
import com.conectaedu.android.ui.register.RegisterScreen
import com.conectaedu.android.ui.studygroup.StudyGroupScreen

@Composable
fun Navigation(viewModel: NavigationViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    val navController = rememberNavController()

    val startDestination =
        if (uiState.isLoggedIn) Screen.Home.route else Screen.Login.route

    if (!uiState.isLoading) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(
                route = Screen.Login.route,
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                LoginScreen(navController = navController)
            }

            composable(
                route = Screen.Register.route,
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                RegisterScreen(navController = navController)
            }

            composable(
                route = Screen.Home.route,
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                HomeScreen(navController = navController)
            }

            composable(
                route = Screen.AreaRegistration.route,
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                AreaRegistrationScreen(navController = navController)
            }

            composable(
                route = "${Screen.Area.route}/{areaId}",
                arguments = listOf(navArgument("areaId") { type = NavType.StringType }),
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                AreaScreen(
                    areaId = it.arguments?.getString("areaId") ?: "",
                    navController = navController
                )
            }

            composable(
                route = "${Screen.Course.route}/{areaId}/{courseId}",
                arguments = listOf(
                    navArgument("areaId") { type = NavType.StringType },
                    navArgument("courseId") { type = NavType.StringType }),
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                CourseScreen(
                    areaId = it.arguments?.getString("areaId") ?: "",
                    courseId = it.arguments?.getString("courseId") ?: "",
                    navController = navController
                )
            }

            composable(
                route = "${Screen.StudyGroup.route}/{areaId}/{courseId}/{studyGroupId}",
                arguments = listOf(
                    navArgument("areaId") { type = NavType.StringType },
                    navArgument("courseId") { type = NavType.StringType },
                    navArgument("studyGroupId") { type = NavType.StringType }
                ),
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                StudyGroupScreen(
                    areaId = it.arguments?.getString("areaId") ?: "",
                    courseId = it.arguments?.getString("courseId") ?: "",
                    studyGroupId = it.arguments?.getString("studyGroupId") ?: "",
                    navController = navController
                )
            }
        }
    }
}