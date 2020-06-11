package com.heixss.github.di.modules

import android.content.Context
import com.heixss.github.GithubApp
import com.heixss.github.network.GithubApi
import com.heixss.github.network.HeadersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: GithubApp): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            // Moshi maps JSON to classes
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, headersInterceptor: HeadersInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(headersInterceptor)
        client.addInterceptor(
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        client.cache(cache)
        client.connectionPool(ConnectionPool(5, 3, TimeUnit.SECONDS))
        return client.build()
    }

    @Singleton
    @Provides
    fun provideOkHttpCache(context: Context): Cache {
        return Cache(File(context.cacheDir, "okhttp-cache"), (3 * 1000 * 1000).toLong())
    }

}