package com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation

//sealed class Screen(route: String) {
//    object PeopleScreen: Screen(route = "people")
//    object ArticlesScreen: Screen(route = "articles")
//}

enum class Screen(val routes:String) {
    PEOPLE("people"),
    ARTICLE_SCREEN("articles")
}