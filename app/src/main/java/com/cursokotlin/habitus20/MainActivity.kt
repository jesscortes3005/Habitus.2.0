package com.cursokotlin.habitus20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.cursokotlin.habitus20.screen.LoginScreen
import com.cursokotlin.habitus20.screen.RegisterScreen
import com.cursokotlin.habitus20.ui.theme.Habitus20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Habitus20Theme {
                var currentScreen by remember { mutableStateOf("login") }

                if (currentScreen == "login") {
                    LoginScreen(onRegisterClick = { currentScreen = "register" })
                } else {
                    RegisterScreen(onLoginClick = { currentScreen = "login" })
                }
            }
        }
    }
}
