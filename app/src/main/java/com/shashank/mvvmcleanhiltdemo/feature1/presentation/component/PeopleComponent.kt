package com.shashank.mvvmcleanhiltdemo.feature1.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shashank.mvvmcleanhiltdemo.feature1.data.mapper.toDomainData
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.state.PeopleViewModelState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PeopleComponent(modifier: Modifier, peopleViewModelState: PeopleViewModelState) {
    if(peopleViewModelState.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    } else if (peopleViewModelState.errorMessage.isNullOrEmpty()) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(
                text = peopleViewModelState.errorMessage.toString(),
                modifier = modifier.align(Alignment.Center),
                color = Color.Blue
            )
        }
    }

    if(!peopleViewModelState.people?.data.isNullOrEmpty()) {
        LazyColumn {
            peopleViewModelState.people?.let {
                items(it.data) {
                    ItemComposable(modifier = modifier, data =  it.toDomainData())
                }
            }
        }
    }
}
