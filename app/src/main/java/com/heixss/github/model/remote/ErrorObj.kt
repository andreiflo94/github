package com.heixss.github.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

@JsonClass(generateAdapter = true)
data class ErrorObj(@Json(name = "message") val message: String) {

    companion object {
        fun of(responseBody: ResponseBody): ErrorObj? {
            val moshi = Moshi.Builder().build()
            return moshi.adapter(ErrorObj::class.java).fromJson(responseBody.string())
        }
    }
}