package com.heixss.github.model.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class License(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "spdx_id")
    val spdxId: String = "",
    @Json(name = "key")
    val key: String = "",
    @Json(name = "url")
    val url: String? = "",
    @Json(name = "node_id")
    val nodeId: String = ""
) : Parcelable