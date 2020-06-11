package com.heixss.github.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReposResponse(
    @Json(name = "total_count")
    val totalCount: Int = 0,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean = false,
    @Json(name = "items")
    val items: List<RepoDetails>?
)