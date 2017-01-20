package com.anko.stinodes.ankoplication.web

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "http://www.masterani.me/"
val IMAGE_URL = "http://cdn.masterani.me/"

fun createMAService() = Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(
                OkHttpClient.Builder()
                        .addInterceptor(
                                fun ():Interceptor {
                                    val interceptor = HttpLoggingInterceptor()
                                    interceptor.level = BODY
                                    return interceptor
                                }()
                        )
                        .build()
        )
        .build()
        .create(MAService::class.java)
