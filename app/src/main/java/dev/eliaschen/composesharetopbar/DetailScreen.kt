package dev.eliaschen.composesharetopbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navController: NavHostController, data: DataModel) {
    val item = data.mediaItems[data.currentIndex]
    Column() {
        IconButton(onClick = {
            navController.navigate(Screen.Home.name)
        }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Text(item.title, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(item.content)
        }
    }
}