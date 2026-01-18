package com.shiv.admin_panel

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import org.shiv.shared.ButtonPrimary
import org.shiv.shared.FontSize
import org.shiv.shared.IconPrimary
import org.shiv.shared.Resources
import org.shiv.shared.Surface
import org.shiv.shared.TextPrimary
import org.shiv.shared.bebesNeueFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen(
    navigateBack:()->Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Admin Panel",
                        fontFamily = bebesNeueFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = "Back Arrow icon",
                            tint = IconPrimary
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* TODO: Implement action */ }
                    ) {
                        Icon(
                            painter = painterResource(Resources.Icon.Search),
                            contentDescription = "Settings icon",
                            tint = IconPrimary
                        )
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = ButtonPrimary,
                contentColor = IconPrimary,
            ) {
                Icon(
                    painter = painterResource(Resources.Icon.Plus),
                    contentDescription = "Add Icon",
                    tint = TextPrimary
                )
            }
        }
    ) {paddingValues ->   }
}