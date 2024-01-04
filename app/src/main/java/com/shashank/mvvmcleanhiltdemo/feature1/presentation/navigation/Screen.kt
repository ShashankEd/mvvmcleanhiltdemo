package com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation

sealed class Screen(route: String) {
    object PeopleScreen: Screen(route = "people")
}