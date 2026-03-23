package com.cursokotlin.habitus20.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.habitus20.screen.componentes.CustomTextField
import com.cursokotlin.habitus20.ui.theme.theme.HabitusPurple
import com.cursokotlin.habitus20.ui.theme.theme.HabitusLightGray
import com.cursokotlin.habitus20.ui.theme.theme.HabitusDarkPurple
import com.cursokotlin.habitus20.ui.theme.theme.Habitus20Theme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(onRegisterClick: () -> Unit = {}, onLoginSuccess: () -> Unit = {}) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val geometricFont = FontFamily.SansSerif

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
                    .size(180.dp)
                    .shadow(elevation = 12.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(HabitusLightGray),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp),
                        tint = HabitusDarkPurple
                    )
                    Text(
                        text = "HABITUS",
                        style = TextStyle(
                            color = HabitusDarkPurple,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = geometricFont,
                            letterSpacing = 3.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                CustomTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = "Correo electronico:"
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Contraseña", color = Color.Gray) },
                    textStyle = TextStyle(color = HabitusDarkPurple, fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = HabitusLightGray,
                        unfocusedContainerColor = HabitusLightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = HabitusDarkPurple,
                        focusedTextColor = HabitusDarkPurple
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña", tint = HabitusDarkPurple)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
                )
            }

            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Button(
                    onClick = {
                        if (correo.isEmpty() || password.isEmpty()) {
                            errorMsg = "Por favor, llena todos los campos"
                        } else {
                            isLoading = true
                            auth.signInWithEmailAndPassword(correo, password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        errorMsg = ""
                                        onLoginSuccess()
                                    } else {
                                        errorMsg = "Correo o contraseña incorrectos"
                                    }
                                }
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Habitus20Theme {
        LoginScreen()
    }
}
