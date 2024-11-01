package com.sameh.androidwithcomposefromaz.my_app.app.di

import com.sameh.androidwithcomposefromaz.my_app.data.remote.RetrofitAPI
import com.sameh.androidwithcomposefromaz.my_app.data.repo.CategoryRepo
import com.sameh.androidwithcomposefromaz.my_app.domain.repo.ICategoryRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitAPI(retrofit: Retrofit): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepo(retrofitAPI: RetrofitAPI): ICategoryRepo {
        return CategoryRepo(retrofitAPI)
    }
}