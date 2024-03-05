package com.shashank.mvvmcleanhiltdemo.feature1.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.shashank.mvvmcleanhiltdemo.feature1.core.utils.Constants
import com.shashank.mvvmcleanhiltdemo.feature1.data.service.PeopleAPI
import com.shashank.mvvmcleanhiltdemo.feature1.data.datasource.NewsDataSource
import com.shashank.mvvmcleanhiltdemo.feature1.data.repository.PeopleRepositoryImpl
import com.shashank.mvvmcleanhiltdemo.feature1.domain.repository.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shashank.mvvmcleanhiltdemo.db.NewsDatabase
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(5,TimeUnit.MINUTES)
        .readTimeout(5,TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun peopleAPI(retrofit: Retrofit) : PeopleAPI = retrofit.create(PeopleAPI::class.java)


    @Provides
    @Singleton
    fun createDriver(@ApplicationContext context: Context): SqlDriver = AndroidSqliteDriver(
        schema = NewsDatabase.Schema,
        context,
        name = "shashank.Database.db"
    )

    @Singleton
    @Provides
    fun getDatabase(driver: SqlDriver): NewsDatabase = NewsDatabase(driver)

    @Singleton
    @Provides
    fun objDataSource(db: NewsDatabase): NewsDataSource = NewsDataSource(db)

   @Provides
    @Singleton
    fun providePeopleRepository(api: PeopleAPI, dataSource: NewsDataSource) : PeopleRepository {
        return PeopleRepositoryImpl(api,dataSource)
    }
}