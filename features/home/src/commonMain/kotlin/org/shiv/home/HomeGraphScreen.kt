package org.shiv.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.shiv.home.components.BottomBar
import org.shiv.home.domain.BottomBarDestination
import org.shiv.shared.FontSize
import org.shiv.shared.IconPrimary
import org.shiv.shared.Resources
import org.shiv.shared.Surface
import org.shiv.shared.TextPrimary
import org.shiv.shared.bebesNeueFont
import org.shiv.shared.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(){
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.ProductOverView.screen.toString()) -> BottomBarDestination.ProductOverView
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.ProductOverView
            }
        }
    }


    Scaffold(contentColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedContent(
                        targetState = selectedDestination
                    ) { destination ->
                        Text(
                            text = destination.title,
                            fontFamily = bebesNeueFont(),
                            fontSize = FontSize.LARGE,
                            color = TextPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ){
                        Icon(painter = painterResource(Resources.Icon.Menu), contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        }


        ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )

        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.ProductOverView,
                modifier = Modifier.weight(1f)
            ){
                composable<Screen.ProductOverView> {  }
                composable<Screen.Cart> {  }
                composable<Screen.Categories> {  }
            }
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Box(
                modifier = Modifier.padding(12.dp)
            ) {
                BottomBar(
                    selected = selectedDestination,
                    onSelect = {destination->
                        navController.navigate(destination.screen) {
                            launchSingleTop = true
                            popUpTo<Screen.ProductOverView> {
                                saveState = true
                                inclusive = false
                            }
                            restoreState = true
                        }

                    }
                )
            }
        }
    }
}



