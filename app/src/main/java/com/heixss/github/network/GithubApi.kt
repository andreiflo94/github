package com.heixss.github.network

import com.heixss.github.model.remote.ReposResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/repositories?q=android+language:kotlin&sort=stars")
    suspend fun getAndroidRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ReposResponse>
}