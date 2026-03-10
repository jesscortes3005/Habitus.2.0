package com.cursokotlin.habitus20.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.habitus20.ui.theme.HabitusPurple
import com.cursokotlin.habitus20.ui.theme.HabitusLightGray
import com.cursokotlin.habitus20.ui.theme.HabitusDarkPurple

@Composable
fun ProfileScreen() {
    val geometricFont = FontFamily.SansSerif

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(HabitusPurple)
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Mini Logo Circle
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(1.dp, Color.Gray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Using a placeholder icon for the logo
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = HabitusDarkPurple
                            )
                            Text(
                                text = "HABITUS",
                                style = TextStyle(
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = HabitusDarkPurple
                                )
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text(
                        text = "Mi Perfil",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = geometricFont
                        )
                    )
                    
                    Spacer(modifier = Modifier.weight(1.5f))
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(HabitusPurple),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(Icons.Default.Build, "Mis Objetivos", false)
                    BottomNavItem(Icons.Default.List, "Dieta", false)
                    BottomNavItem(Icons.Default.Settings, "Ajustes", false)
                    BottomNavItem(Icons.Default.Person, "Perfil", true)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Info Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Large Profile Icon
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .shadow(6.dp, CircleShape)
                        .clip(CircleShape)
                        .background(HabitusLightGray)
                        .border(2.dp, HabitusDarkPurple.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(70.dp),
                        tint = HabitusDarkPurple
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text(
                        text = "User",
                        style = TextStyle(
                            color = HabitusDarkPurple,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = geometricFont
                        )
                    )
                    Text(
                        text = "Example123@gmail.com",
                        style = TextStyle(
                            color = HabitusDarkPurple.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = geometricFont
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            HorizontalDivider(color = HabitusDarkPurple, thickness = 1.dp)
            
            Spacer(modifier = Modifier.height(24.dp))

            // Objetivos Title
            Text(
                text = "Objetivos",
                style = TextStyle(
                    color = HabitusDarkPurple,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = geometricFont
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Objective Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ObjectiveCard(Icons.Default.Star, "4", "Cumplidos")
                ObjectiveCard(Icons.Default.Notifications, "5", "Pendientes")
                ObjectiveCard(Icons.Default.Favorite, "4", "Estrellas")
            }

            Spacer(modifier = Modifier.weight(1f))

            // Cerrar Sesión Button
            Button(
                onClick = { /* Logout */ },
                colors = ButtonDefaults.buttonColors(containerColor = HabitusLightGray),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(45.dp)
                    .shadow(4.dp, RoundedCornerShape(25.dp))
            ) {
                Text(
                    text = "Cerrar Sesión",
                    style = TextStyle(
                        color = HabitusDarkPurple,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = geometricFont
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ObjectiveCard(icon: ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .size(width = 100.dp, height = 110.dp)
                .shadow(6.dp, RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, HabitusDarkPurple.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = HabitusDarkPurple
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = value,
                    style = TextStyle(
                        color = HabitusDarkPurple,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = TextStyle(
                color = HabitusDarkPurple,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(28.dp),
            tint = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f)
        )
        Text(
            text = label,
            style = TextStyle(
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
