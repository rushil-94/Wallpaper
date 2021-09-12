package com.rushil.wallpaper

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rushil.wallpaper.misc.Screen
import com.rushil.wallpaper.ui.fragmentscreens.TopicScreen
import com.rushil.wallpaper.ui.theme.WallpaperTheme
import com.rushil.wallpaper.viewmodels.MainViewModel
import com.rushil.wallpaper.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }

    @Composable
    fun MainActivityScreen() {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        WallpaperTheme {
            Surface(color = MaterialTheme.colors.background) {
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
                    BottomNavigationNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf<Screen>(Screen.Home, Screen.Topic, Screen.Search, Screen.Profile)
        BottomNavigation {
            val backStack = navController.currentBackStackEntryAsState()
            val curDestination = backStack?.value?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    selected = curDestination == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = ""
                        )
                    },
                    label = { Text(stringResource(id = item.resourceId)) })
            }
        }
    }

    @Composable
    fun BottomNavigationNavHost(
        navController: NavHostController,
        modifier: Modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Topic.route,
            modifier = modifier
        ) {
            composable(route = Screen.Home.route) {
                Text(Screen.Home.route)
            }
            composable(route = Screen.Search.route) { Text(Screen.Search.route) }

            composable(route = Screen.Topic.route) {
                val modelView: MainViewModel =
                    viewModel(factory = MainViewModelFactory(this@MainActivity.application))
                TopicScreen(model = modelView)
            }
            composable(route = Screen.Profile.route) { Text(Screen.Profile.route) }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WallpaperTheme {

        }
    }
}


