package com.app.coroutinesmvp.deps

import com.app.coroutinesmvp.api.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASEURL = "https://api.themoviedb.org"

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) =
        retrofit.create(MovieApi::class.java)

}