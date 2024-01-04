package com.shashank.mvvmcleanhiltdemo.feature1.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shashank.mvvmcleanhiltdemo.feature1.domain.model.Data

@Composable
fun ItemComposable(modifier: Modifier, data: Data) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Card(elevation = CardDefaults.cardElevation(8.dp), shape = RoundedCornerShape(8.dp)) {
            Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                 Text(text = data.employee_name, fontWeight = FontWeight.Bold)
//                 Text(text = data.employee_salary, fontWeight = FontWeight.Bold)
            }
        }
    }
}