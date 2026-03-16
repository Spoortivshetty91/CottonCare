package com.example.shrinidhi.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shrinidhi.navigation.NavRoutes
import com.example.shrinidhi.ui.theme.TextSecondary

@Composable
fun ProfileScreen(navController: NavController) {
    var isSignedIn by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var totalScans by remember { mutableStateOf(0) }
    var healthyScans by remember { mutableStateOf(0) }

    val successRate = if (totalScans > 0) "${(healthyScans * 100 / totalScans)}%" else "—"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text("Your Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            if (!isSignedIn) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        isSignedIn = true
                        totalScans = 5
                        healthyScans = 3
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sign In / Sign Up")
                }
            } else {
                Text("Welcome, ${email.text}", color = TextSecondary)
            }
        }

        if (isSignedIn && totalScans > 0) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatItem(totalScans.toString(), "Total Scans")
                    StatItem(healthyScans.toString(), "Healthy")
                    StatItem(successRate, "Success")
                }
            }

            item {
                Column {
                    Text("Achievements", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AchievementBadge("🏅", "Early Adopter")
                        AchievementBadge("🌿", "Plant Expert")
                        AchievementBadge("📊", "Data Driven")
                    }
                }
            }

            item {
                Column {
                    Text("Recent Activity", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("• Scanned $totalScans plants this week", color = TextSecondary)
                    Text("• Learned about Fusarium Wilt", color = TextSecondary)
                    Text("• Added 3 plants to tracking", color = TextSecondary)
                }
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                MenuItem("Settings", Icons.Default.Settings) {
                    navController.navigate(NavRoutes.SETTINGS)
                }
                MenuItem("Help & Support", Icons.Default.Help) {
                    navController.navigate(NavRoutes.HELP)
                }
                if (isSignedIn) {
                    MenuItem("Logout", Icons.Default.Logout) {
                        isSignedIn = false
                        email = TextFieldValue("")
                        password = TextFieldValue("")
                        totalScans = 0
                        healthyScans = 0
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(label, color = TextSecondary)
    }
}

@Composable
fun AchievementBadge(emoji: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji, fontSize = 24.sp)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}

@Composable
fun MenuItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF4CAF50))
        Spacer(Modifier.width(12.dp))
        Text(label)
    }
}