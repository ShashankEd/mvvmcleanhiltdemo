package com.shashank.mvvmcleanhiltdemo.feature1.di

import com.shashank.mvvmcleanhiltdemo.feature1.core.utils.Constants
import com.shashank.mvvmcleanhiltdemo.feature1.data.api.PeopleAPI
import com.shashank.mvvmcleanhiltdemo.feature1.data.repository.PeopleRepositoryImpl
import com.shashank.mvvmcleanhiltdemo.feature1.domain.repository.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providePeopleRepository(api: PeopleAPI) : PeopleRepository {
        return PeopleRepositoryImpl(api)
    }

}