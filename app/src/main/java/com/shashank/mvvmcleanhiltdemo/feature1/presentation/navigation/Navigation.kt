package com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.component.ArticleComposable
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.component.PeopleComponent
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel.PeopleViewModel

@Composable
fun Navigation(peopleViewModel: PeopleViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ARTICLE_SCREEN.routes) {
        composable(Screen.PEOPLE.routes) {
            val peopleState = peopleViewModel.peopleState.collectAsState().value
            PeopleComponent(modifier = Modifier, peopleViewModelState = peopleState )
        }

        composable(Screen.ARTICLE_SCREEN.routes) {
//            ArticleComposable(modifier = Modifier, peopleViewModel = peopleViewModel )
        }
    }
}