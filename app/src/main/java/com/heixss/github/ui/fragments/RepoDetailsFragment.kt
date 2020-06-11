package com.heixss.github.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.heixss.github.R
import com.heixss.github.databinding.RepoDetailsFragmentBinding
import com.heixss.github.viewmodels.RepoDetailsViewModel

class RepoDetailsFragment : BaseFragment() {

    private val viewModel: RepoDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.repoDetails = RepoDetailsFragmentArgs.fromBundle(it).repoDetails
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repoDetailsFragmentBinding: RepoDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.repo_details_fragment, container, false)
        repoDetailsFragmentBinding.repoDetails = viewModel.repoDetails
        return repoDetailsFragmentBinding.root
    }
}
