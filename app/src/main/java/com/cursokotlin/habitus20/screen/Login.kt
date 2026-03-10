package com.cursokotlin.habitus20.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.habitus20.ui.theme.HabitusPurple
import com.cursokotlin.habitus20.ui.theme.HabitusLightGray
import com.cursokotlin.habitus20.ui.theme.HabitusDarkPurple

@Composable
fun LoginScreen(onRegisterClick: () -> Unit = {}, onLoginSuccess: () -> Unit = {}) {
    var usuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    val geometricFont = FontFamily.SansSerif

    // Email regex: letters, optional numbers, @, and domain
    val emailRegex = "^[a-zA-Z]+[0-9]*@[a-zA-Z]+\\.[a-zA-Z]+$".toRegex()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HabitusPurple)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Iniciar Sesión",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = geometricFont,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Box(
                modifier = Modifier
                    .size(220.dp)
                    .shadow(elevation = 12.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(HabitusLightGray),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                        contentDescription = null,
                        modifier = Modifier.size(85.dp),
                        tint = HabitusDarkPurple
                    )
                    Text(
                        text = "HABITUS",
                        style = TextStyle(
                            color = HabitusDarkPurple,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = geometricFont,
                            letterSpacing = 3.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Usuario Field with restrictions
            Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                CustomTextField(
                    value = usuario,
                    onValueChange = { newValue ->
                        // Only allow letters and max 24 chars
                        val filtered = newValue.filter { it.isLetter() }.take(24)
                        usuario = filtered
                    },
                    label = "Usuario:"
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Correo Field
            Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                CustomTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = "Correo electronico:"
                )
            }

            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = {
                    if (usuario.isEmpty()) {
                        errorMsg = "El usuario no puede estar vacío"
                    } else if (!emailRegex.matches(correo)) {
                        errorMsg = "Correo inválido (Nombre+números opcionales@dominio)"
                    } else {
                        errorMsg = ""
                        onLoginSuccess()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = HabitusLightGray),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Iniciar sesión",
                    style = TextStyle(
                        color = HabitusDarkPurple,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = geometricFont
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = onRegisterClick) {
                Text(
                    text = "Registrate",
                    style = TextStyle(
                        color = HabitusDarkPurple,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = geometricFont,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.height(45.dp))

            TextButton(onClick = { /* Handle Forgot Password */ }) {
                Text(
                    text = "Olvidaste tu contraseña?",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = geometricFont,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}
