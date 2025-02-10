package com.karine.eventosapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.karine.eventosapp.api.retrofitService
import com.karine.eventosapp.database.dao.UserDao
import com.karine.eventosapp.database.db.AppDatabase
import com.karine.eventosapp.ui.screens.addDonation.AddDonationScreen
import com.karine.eventosapp.ui.screens.addDonation.AddDonationViewModel
import com.karine.eventosapp.ui.screens.authentication.AuthViewModel
import com.karine.eventosapp.ui.screens.authentication.LoginScreen
import com.karine.eventosapp.ui.screens.authentication.RegisterScreen
import com.karine.eventosapp.ui.screens.edit.EditScreen
import com.karine.eventosapp.ui.screens.edit.EditViewModel
import com.karine.eventosapp.ui.screens.home.HomeScreen
import com.karine.eventosapp.ui.screens.home.HomeViewModel
import com.karine.eventosapp.ui.screens.route.AddDonationRoute
import com.karine.eventosapp.ui.screens.route.EditDonationRoute
import com.karine.eventosapp.ui.screens.route.HomeRoute
import com.karine.eventosapp.ui.screens.route.LoginRoute
import com.karine.eventosapp.ui.screens.route.RegisterRoute
import com.karine.eventosapp.ui.theme.MoneyFlowTheme

class MainActivity : ComponentActivity() {

    private lateinit var userDao: UserDao
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var addDonationViewModel: AddDonationViewModel
    private lateinit var editViewModel: EditViewModel
    private lateinit var authViewModel: AuthViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val apiService = retrofitService()
        homeViewModel = HomeViewModel(userDao, apiService)
        addDonationViewModel = AddDonationViewModel(userDao, apiService)

        authViewModel = AuthViewModel(userDao, apiService)

        enableEdgeToEdge()
        setContent {
            MoneyFlowTheme {
                var navController = rememberNavController()
                val user = authViewModel.user.value
                val startScreen = if (user != null) HomeRoute else LoginRoute

                NavHost(
                    navController = navController,
                    startDestination = startScreen
                ) {

                    composable<LoginRoute> {
                        LoginScreen(
                            viewmodel = authViewModel,
                            onLogin = { navController.navigate(HomeRoute) },
                            onRegister = { navController.navigate(RegisterRoute) }
                        )
                    }
                    composable<RegisterRoute> {
                        RegisterScreen(
                            viewmodel = authViewModel,
                            onLogin = { navController.navigate(LoginRoute) }
                        )
                    }
                    composable<HomeRoute> {
                        HomeScreen(
                            viewModel = homeViewModel,
                            createDonation = { navController.navigate(AddDonationRoute) },
                            onLogout = { navController.navigate(LoginRoute) },
                            onDonationClick = { transactionId ->
                                navController.navigate(EditDonationRoute(transactionId))
                            }
                        )
                    }
                    composable<EditDonationRoute> { backStackEntry ->
                        val transactionId = backStackEntry.toRoute<EditDonationRoute>().donationId
                        editViewModel = EditViewModel(this@MainActivity.userDao, apiService, transactionId)
                        EditScreen(
                            viewModel = editViewModel,
                            onNavigateBack = { navController.navigate(HomeRoute) }
                        )
                    }
                    composable<AddDonationRoute> {
                        AddDonationScreen(
                            viewModel = addDonationViewModel,
                            onNavigateBack = {
                                navController.navigate(HomeRoute)
                            }
                        )
                    }
                }
            }
        }
    }
}