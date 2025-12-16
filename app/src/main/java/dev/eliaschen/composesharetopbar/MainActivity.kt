package dev.eliaschen.composesharetopbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.eliaschen.composesharetopbar.ui.theme.ComposeShareTopbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeShareTopbarTheme {
                Nav(this)
            }
        }
    }
}

@Composable
fun Nav(activity: MainActivity) {
    val navController = rememberNavController()
    val data = ViewModelProvider(activity)[DataModel::class.java]
    val disableTopBar = listOf(Screen.Detail.name)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(topBar = {
        if (currentDestination?.route !in disableTopBar) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .statusBarsPadding()
            ) {
                IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding), enterTransition = {
                EnterTransition.None
            }, exitTransition = { ExitTransition.None }
        ) {
            composable(Screen.Home.name) {
                HomeScreen(navController, data)
            }
            composable(Screen.Detail.name) {
                DetailScreen(navController, data)
            }
        }
    }
}


enum class Screen(val title: String) {
    Home(title = "首頁"),
    Detail(title = "詳細資訊")
}