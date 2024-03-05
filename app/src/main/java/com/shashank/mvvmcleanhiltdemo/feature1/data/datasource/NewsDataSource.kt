package com.shashank.mvvmcleanhiltdemo.feature1.data.datasource

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.shashank.jetpackmvvmcleanretrofit.feature1.domain.model.Article
import shashank.mvvmcleanhiltdemo.db.NewsDatabase
import javax.inject.Inject

class NewsDataSource @Inject constructor(val database: NewsDatabase) {

    fun getArticlesFromDb() : List<Article>  =
        database.newsDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    private fun mapToArticleRaw(
        author:String,
        title: String?,
        urlToImage: String,
        publishedAt: String?
    ) : Article = Article(author,title, urlToImage, publishedAt)

    fun insertArticles(articles: List<Article>) =
        database.newsDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }

    private fun insertArticle(articleRaw: Article)  =
        database.newsDatabaseQueries.insertArticle(
            articleRaw.author?: "",
            articleRaw.title?: "",
            articleRaw.urlToImage?: "",
            articleRaw.publishedAt?: ""
        )

    fun clearArticles() =
        database.newsDatabaseQueries.removeAllArticles()

}
