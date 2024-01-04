package com.shashank.mvvmcleanhiltdemo.feature1.core.common

sealed class Resource<T>(val status: String? = null, val people: T? = null){
    class Success<T>(people: T?): Resource<T>(people = people)
    class Error<T>(status: String?): Resource<T>(status = status)
    class Loading<T>: Resource<T>()
}