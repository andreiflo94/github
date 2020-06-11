package com.heixss.github.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.heixss.github.model.remote.ErrorObj
import com.heixss.github.model.remote.RepoDetails
import com.heixss.github.model.repositories.ReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val reposRepository: ReposRepository) :
    BaseViewModel() {

    val liveRepoList = MutableLiveData<List<RepoDetails>>()
    private val repoList = ArrayList<RepoDetails>()

    fun loadRepos(page: Int, perPage: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (page == 1) {
                    repoList.clear()
                    updateLiveData(repoList)
                }
                changeUIStatus(Progress.SHOW)
                val response = reposRepository.loadRepos(page, perPage)
                if (response.isSuccessful) {
                    response.body()?.items?.let {
                        repoList.addAll(repoList.size, it)
                        updateLiveData(repoList)
                    }
                    changeUIStatus(Progress.HIDE)
                } else {
                    changeUIStatus(Progress.HIDE)
                    ErrorObj.of(response.errorBody()!!)?.let { errorObj ->
                        showUIError(errorObj.message)
                    }
                }
            } catch (ex: Exception) {
                changeUIStatus(Progress.HIDE)
                showUIError(ex.message.toString())
            }
        }
    }

    private suspend fun updateLiveData(list: List<RepoDetails>) {
        withContext(Dispatchers.Main) {
            liveRepoList.value = list
        }
    }

    private suspend fun changeUIStatus(progress: Progress) {
        withContext(Dispatchers.Main) {
            refreshSubject.onNext(progress)
        }
    }

    private suspend fun showUIError(errorMsj: String) {
        withContext(Dispatchers.Main) {
            errorSubject.onNext(errorMsj)
        }
    }
}

enum class Progress {
    SHOW,
    HIDE
}
