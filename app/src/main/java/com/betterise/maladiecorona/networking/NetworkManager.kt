package com.betterise.maladiecorona.networking

import android.content.Context
import com.betterise.maladiecorona.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alexandre on 31/08/20.
 */
class NetworkManager(context: Context, val url : String) {
    companion object {
        private lateinit var client: OkHttpClient

        fun getApiClient(context: Context): OkHttpClient {
            if (!::client.isInitialized)
                client = ApiClientProvider().getApiClient()

            return client

        }
    }

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getApiClient(context))
        .build()
        .create(ApiService::class.java)
}