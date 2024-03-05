package com.shashank.mvvmcleanhiltdemo.feature1.data.datasource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import shashank.mvvmcleanhiltdemo.db.NewsDatabase

class NewsDatabaseDriverFactory (private val context: Context) {
    fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = NewsDatabase.Schema,
            context,
            name = "JetpackCleanRetrofit.Database.db"
        )
}