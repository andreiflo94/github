package com.heixss.github.viewmodels

import androidx.lifecycle.ViewModel
import com.heixss.github.model.remote.RepoDetails
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor() : ViewModel() {

    lateinit var repoDetails: RepoDetails
}
