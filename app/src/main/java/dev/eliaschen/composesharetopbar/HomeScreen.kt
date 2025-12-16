package dev.eliaschen.composesharetopbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController, data: DataModel) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        item {
            Text("媒體中心", fontWeight = FontWeight.Bold)
        }
        items(data.mediaItems) { item ->
            Card(modifier = Modifier.padding(vertical = 5.dp), onClick = {
                navController.navigate(Screen.Detail.name)
            }) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(item.title, fontWeight = FontWeight.Bold)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Row {
                            item.hall.forEach { text ->
                                Text(
                                    text,
                                    color = if (text == "1館") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                )
                                Spacer(Modifier.padding(end = 5.dp))
                            }
                        }
                        Text(item.dateTime)
                    }
                }
            }
        }
    }
}