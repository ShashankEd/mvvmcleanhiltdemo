package com.shashank.mvvmcleanhiltdemo.feature1.domain.model

import com.shashank.mvvmcleanhiltdemo.feature1.data.dto.DataDto

data class People(
    val data: List<DataDto>,
    val message: String,
    val status: String
)
