package com.shashank.mvvmcleanhiltdemo.feature1.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.Article
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.state.AllArticlesViewModelState
import com.shashank.mvvmcleanhiltdemo.feature1.presentation.viewmodel.PeopleViewModel

@Composable
fun ArticleComposable(peopleViewModel: PeopleViewModel) {
    val articlesViewModelState = peopleViewModel.articlesState.collectAsState().value
    var swipeState by remember {
        mutableStateOf(false)
    }
//    val modifier = Modifier
    Log.d("ArticleComposable", "ArticleComposable called() ")


    LaunchedEffect("callapi") {
        peopleViewModel.getAllArticles("bitcoin", false)
    }

    //with column Lazylist is not appearing
//    Column {
        MyAppBar({},{})
        if(articlesViewModelState.isLoading) {
            Loader()
        } else if (articlesViewModelState.errorMessage.isNullOrEmpty()) {
            ErrorMessage(articlesViewModelState.errorMessage.toString())
        }
        ArticleListView(swipeState, peopleViewModel, modifier = Modifier, articlesViewModelState)
//    }

}

@Composable
private fun ArticleListView(
    swipeState: Boolean,
    peopleViewModel: PeopleViewModel,
    modifier: Modifier,
    articlesViewModelState: AllArticlesViewModelState
) {
    SwipeRefresh(
        state = SwipeRefreshState(swipeState), onRefresh = {
            peopleViewModel.getAllArticles("bitcoin", true)
        }) {
        LazyColumn(modifier = modifier.fillMaxSize().padding(top = 40.dp)) {
            articlesViewModelState.articles?.let {
                items(it.articles) {
                    Log.d("shashank", "ArticleComposable: ${it.publishedAt}")
                    ArticleItem(modifier = modifier, article = it)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(modifier: Modifier, article: Article) {
    Column (
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        AsyncImage(model = article.urlToImage, contentDescription = "book image")
        Spacer(modifier = modifier.height(4.dp))
        Text(text = article.title?: "")
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = article.publishedAt?: "",
            style = TextStyle(color = Color.Gray),
            modifier = modifier.align(Alignment.End)
        )
        Spacer(modifier = modifier.height(4.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(onAboutButtonClick: () -> Unit, onSourcesButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onSourcesButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Face,
                    contentDescription = "Sources"
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button"
                )
            }
        }
    )
}
@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

