package com.betterise.maladiecorona.networking

import com.betterise.maladiecorona.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by Alexandre on 31/08/20.
 */
class ApiClientProvider {

    /***
     * Provide an okhttp client with custom interceptors
     */
    fun getApiClient() : OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .cache(null)
            .addInterceptor(httpLoggingInterceptor)             // Logging interceptor, allows viewing network call in logcat when in debug build type
            .readTimeout(1, TimeUnit.MINUTES)           // Lazy timeout is lazy
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}