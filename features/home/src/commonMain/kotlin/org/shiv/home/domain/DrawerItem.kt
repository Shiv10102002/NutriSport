package org.shiv.home.domain

import org.jetbrains.compose.resources.DrawableResource
import org.shiv.shared.Resources

enum class DrawerItem(
    val title:String,
    val icon: DrawableResource
) {
    Profile(
        title = "Profile",
        icon = Resources.Icon.Person
    ),
    Blog(
        title = "Blog",
        icon = Resources.Icon.Book
    ),
    Locations(
        title = "Profile",
        icon = Resources.Icon.MapPin
    ),
    Contact(
        title = "Contact Us",
        icon = Resources.Icon.Edit
    ),
    SignOut(
        title = "Sign Out",
        icon = Resources.Icon.SignOut
    ),
    Admin(
        title = "Admin Panel",
        icon = Resources.Icon.Unlock
    ),


}