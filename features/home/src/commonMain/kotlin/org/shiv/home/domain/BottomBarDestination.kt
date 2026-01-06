package org.shiv.home.domain

import org.jetbrains.compose.resources.DrawableResource
import org.shiv.shared.Resources
import org.shiv.shared.navigation.Screen

enum class BottomBarDestination(
    val icon: DrawableResource,
    val title: String,
    val screen: Screen
){
    ProductOverView(
        icon = Resources.Icon.Home,
        title = "Nutri Sport",
        screen = Screen.ProductOverView
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.ProductOverView
    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = "Categories",
        screen = Screen.ProductOverView
    ),
}