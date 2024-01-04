package com.shashank.mvvmcleanhiltdemo.feature1.data.mapper

import com.shashank.mvvmcleanhiltdemo.feature1.data.dto.PeopleDto
import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.People

fun PeopleDto.toDomainPeople(): People{
        return People(data,message,status)
}