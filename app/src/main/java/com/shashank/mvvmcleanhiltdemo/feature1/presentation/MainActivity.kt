package com.shashank.mvvmcleanhiltdemo.feature1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.component.AppScaffold
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation.Navigation
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.ui.theme.JetpackMvvmCleanRetrofitTheme
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackMvvmCleanRetrofitTheme {
                val peopleViewModel = hiltViewModel<PeopleViewModel>()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Navigation(peopleViewModel)
                    AppScaffold(applicationContext,peopleViewModel)
                }
            }
        }
    }
}
