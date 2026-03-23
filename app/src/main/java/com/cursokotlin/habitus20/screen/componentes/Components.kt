package com.cursokotlin.habitus20.screen.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.habitus20.ui.theme.theme.HabitusLightGray
import com.cursokotlin.habitus20.ui.theme.theme.HabitusDarkPurple

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = label, color = Color.Gray) },
        textStyle = TextStyle(
            color = HabitusDarkPurple,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = HabitusLightGray,
            unfocusedContainerColor = HabitusLightGray,
            disabledContainerColor = HabitusLightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = HabitusDarkPurple, // <-- Cursor morado
            focusedLabelColor = HabitusDarkPurple,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = HabitusDarkPurple // <-- Texto morado al escribir
        ),
        singleLine = true
    )
}
