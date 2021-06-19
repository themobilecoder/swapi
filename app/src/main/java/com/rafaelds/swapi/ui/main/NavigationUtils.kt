package com.rafaelds.swapi.ui.main

import androidx.navigation.NavController
import androidx.navigation.NavDirections

object NavigationUtils {
    fun NavController.navigateSafe(navDirections: NavDirections) {
        currentDestination?.getAction(navDirections.actionId)?.run {
            navigate(navDirections)
        }
    }
}