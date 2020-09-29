package com.example.navigation.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Throws


class RetroFitInstance {
     val token="520eeffab917d9d6ec292c016d2ec631c354a926977db9a1104c685275906180"
    private var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", " Bearer $token")
                .addHeader("Accept","application/json")
                .build()
            return chain.proceed(newRequest)
        }
    }).build()

    private var baseUrl="https://gorest.co.in/public-api/"
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}