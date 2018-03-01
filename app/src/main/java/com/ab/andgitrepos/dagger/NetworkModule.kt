package com.ab.andgitrepos.dagger

import com.ab.andgitrepos.BuildConfig
import com.ab.andgitrepos.datasource.retrofit.GitApiClient
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by sunde_000 on 28/02/2018.
 */
@Module
class NetworkModule(val mBaseUrl: String) {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providerSearchClient(retrofit: Retrofit): GitApiClient {
        return retrofit.create(GitApiClient::class.java)
    }

}