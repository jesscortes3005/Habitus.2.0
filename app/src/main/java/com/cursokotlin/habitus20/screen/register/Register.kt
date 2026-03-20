package com.cursokotlin.habitus20.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.habitus20.screen.componentes.CustomTextField
import com.cursokotlin.habitus20.ui.theme.theme.HabitusPurple
import com.cursokotlin.habitus20.ui.theme.theme.HabitusLightGray
import com.cursokotlin.habitus20.ui.theme.theme.HabitusDarkPurple

// Simple Singleton to store user data in memory for this session
object UserDataStore {
    var savedUser by mutableStateOf("")
    var savedEmail by mutableStateOf("")
    var savedPassword by mutableStateOf("")
}

@Composable
fun RegisterScreen(onLoginClick: () -> Unit = {}, onRegisterSuccess: () -> Unit = {}) {
    var user by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    val geometricFont = FontFamily.SansSerif
    
    val emailRegex = "^[a-zA-Z]+[0-9]*@[a-zA-Z]+\\.[a-zA-Z]+$".toRegex()
    
    fun isPasswordValid(pass: String): Boolean {
        if (pass.length < 12) return false
        val hasUppercase = pass.any { it.isUpperCase() }
        val hasDigit = pass.any { it.isDigit() }
        val hasSymbol = pass.any { !it.isLetterOrDigit() }
        return hasUppercase && hasDigit && hasSymbol
    }

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
                text = "Registrarse",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = geometricFont,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(bottom = 30.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                    CustomTextField(
                        value = user,
                        onValueChange = { newValue ->
                            val filtered = newValue.filter { it.isLetter() }.take(24)
                            user = filtered
                        },
                        label = "Usuario:"
                    )
                }

                Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Correo electronico:"
                    )
                }

                Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text(text = "Contraseña", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = HabitusLightGray,
                            unfocusedContainerColor = HabitusLightGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )
                }

                Box(modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))) {
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = { Text(text = "Confirmar Contraseña:", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = HabitusLightGray,
                            unfocusedContainerColor = HabitusLightGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )
                }
            }

            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Yellow,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = HabitusLightGray,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(10.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = HabitusDarkPurple,
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(
                        text = "Aceptar nuestros términos y condiciones",
                        color = Color.DarkGray,
                        fontSize = 12.sp,
                        fontFamily = geometricFont
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (user.isEmpty()) {
                        errorMsg = "El usuario debe contener solo letras"
                    } else if (!emailRegex.matches(email)) {
                        errorMsg = "Correo inválido (Formato: Nombre+Nros@dominio)"
                    } else if (!isPasswordValid(password)) {
                        errorMsg = "Contraseña: 12 caracteres, Mayúscula, Número y Símbolo"
                    } else if (password != confirmPassword) {
                        errorMsg = "Las contraseñas no coinciden"
                    } else if (!termsAccepted) {
                        errorMsg = "Debes aceptar los términos y condiciones"
                    } else {
                        // SAVE DATA TO STORE
                        UserDataStore.savedUser = user
                        UserDataStore.savedEmail = email
                        UserDataStore.savedPassword = password
                        
                        errorMsg = ""
                        onRegisterSuccess()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = HabitusLightGray),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Registrarse",
                    style = TextStyle(
                        color = HabitusDarkPurple,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = geometricFont
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            TextButton(onClick = onLoginClick) {
                Text(
                    text = "Ya tienes cuenta? Inicia sesión",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        fontFamily = geometricFont,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}
