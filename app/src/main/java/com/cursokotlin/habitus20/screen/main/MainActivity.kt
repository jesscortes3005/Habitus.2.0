package com.cursokotlin.habitus20.screen.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.cursokotlin.habitus20.screen.login.LoginScreen
import com.cursokotlin.habitus20.screen.register.RegisterScreen
import com.cursokotlin.habitus20.screen.pages.ProfileScreen
import com.cursokotlin.habitus20.ui.theme.theme.Habitus20Theme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Forzamos inicialización de Firebase por si acaso
            FirebaseApp.initializeApp(this)
            val auth = FirebaseAuth.getInstance()
            
            enableEdgeToEdge()
            setContent {
                Habitus20Theme {
                    var currentScreen by remember { 
                        mutableStateOf(if (auth.currentUser != null) "dashboard" else "login") 
                    }

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
                            ProfileScreen(
                                onLogout = { currentScreen = "login" },
                                onDashboardClick = { currentScreen = "dashboard" }
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error durante el inicio: ${e.message}")
        }
    }
}
