package com.ab.andgitrepos.dagger

import com.ab.andgitrepos.datasource.retrofit.SearchClient
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
        return OkHttpClient.Builder().build()
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
    fun providerSearchClient(retrofit: Retrofit): SearchClient {
        return retrofit.create(SearchClient::class.java)
    }

}