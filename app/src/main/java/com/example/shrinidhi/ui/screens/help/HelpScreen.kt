package com.example.shrinidhi.ui.screens.help

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
@Composable
fun HelpScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Help & Support", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("• How to scan a plant")
        Text("• Understanding results")
        Text("• Contact support")
        Text("• FAQs")
    }
}