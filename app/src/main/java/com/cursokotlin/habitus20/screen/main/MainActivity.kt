package com.cursokotlin.habitus20.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.cursokotlin.habitus20.screen.login.LoginScreen
import com.cursokotlin.habitus20.screen.register.RegisterScreen
import com.cursokotlin.habitus20.screen.pages.ProfileScreen
import com.cursokotlin.habitus20.ui.theme.theme.Habitus20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Habitus20Theme {
                var currentScreen by remember { mutableStateOf("login") }

                when (currentScreen) {
                    "login" -> {
                        LoginScreen(
                            onRegisterClick = { currentScreen = "register" },
                            onLoginSuccess = { currentScreen = "dashboard" }
                        )
                    }
                    "register" -> {
                        RegisterScreen(
                            onLoginClick = { currentScreen = "login" },
                            onRegisterSuccess = { 
                                currentScreen = "login" 
                            }
                        )
                    }
                    "dashboard" -> {
                        DashboardScreen(onProfileClick = { currentScreen = "profile" })
                    }
                    "profile" -> {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}
