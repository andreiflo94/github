package com.heixss.github.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.github.mercy-preview+json")
            .build()
        return chain.proceed(request)
    }
}