package com.shashank.mvvmcleanhiltdemo.feature1.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation.Screen
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel.PeopleViewModel

@Composable
fun AppScaffold(context: Context,peopleViewModel: PeopleViewModel) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            context = context,
            peopleViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    context: Context,
    peopleViewModel:PeopleViewModel
) {
    NavHost(navController = navController, startDestination = Screen.ARTICLE_SCREEN.routes) {
        composable(Screen.PEOPLE.routes) {
            val peopleState = peopleViewModel.peopleState.collectAsState().value
            PeopleComponent(modifier = modifier, peopleViewModelState = peopleState )
        }

        composable(Screen.ARTICLE_SCREEN.routes) {
            ArticleComposable( peopleViewModel = peopleViewModel )
        }
    }
}
