package com.shashank.mvvmcleanhiltdemo.feature1.data.mapper

import com.shashank.mvvmcleanhiltdemo.feature1.data.dto.DataDto
import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.Data

fun DataDto.toDomainData(): Data {
    return Data(employee_age,employee_name,employee_salary,id,profile_image)
}