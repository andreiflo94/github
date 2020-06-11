package com.heixss.github.model.repositories

import com.heixss.github.model.remote.ReposResponse
import com.heixss.github.network.GithubApi
import retrofit2.Response
import javax.inject.Inject

class ReposRepository @Inject constructor(private val githubApi: GithubApi) {

    suspend fun loadRepos(page: Int, perPage: Int): Response<ReposResponse> {
        return githubApi.getAndroidRepos(page, perPage)
    }
}