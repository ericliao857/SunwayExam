package com.example.sunwayexam.di

import android.app.Application
import android.content.Context
import com.example.sunwayexam.data.StorageRepository
import com.example.sunwayexam.data.StorageRepositoryImpl
import com.example.sunwayexam.data.api.ApiService
import com.example.sunwayexam.data.TravelTaipeiRepository
import com.example.sunwayexam.data.TravelTaipeiRepositoryImpl
import com.example.sunwayexam.data.local.LanguageSharedPref
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindOpenDataRepository(repository: TravelTaipeiRepositoryImpl): TravelTaipeiRepository

    @Singleton
    @Binds
    abstract fun bindStorageRepository(repository: StorageRepositoryImpl): StorageRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Singleton
    @Provides
    fun providerOkHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        )
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLanguageSharedPref(application: Application): LanguageSharedPref {
        return LanguageSharedPref(application)
    }
}