package com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.component.PeopleComponent
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel.PeopleViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "people") {
        composable("people") {
            val peopleViewModel = hiltViewModel<PeopleViewModel>()
            val peopleState = peopleViewModel.peopleState.collectAsState().value
            PeopleComponent(modifier = Modifier, peopleViewModelState = peopleState )
        }
    }
}