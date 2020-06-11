package com.heixss.github.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heixss.github.R
import com.heixss.github.ui.adapter.ItemVerticalSpacingDecorator
import com.heixss.github.ui.adapter.ReposAdapter
import com.heixss.github.viewmodels.Progress
import com.heixss.github.viewmodels.ReposViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.repos_fragment.*

const val PER_PAGE_DEFAULT = 10
const val LIST_LOAD_THRESHOLD = 4

class ReposFragment : BaseFragment(R.layout.repos_fragment) {

    private lateinit var reposAdapter: ReposAdapter
    private val viewModel: ReposViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadRepos(1, PER_PAGE_DEFAULT)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.liveRepoList.observe(viewLifecycleOwner, Observer {
            reposAdapter.updateList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configUi()
    }

    override fun onResume() {
        super.onResume()
        observeCardClickSubject()
        observeRefreshSubject()
        observeErrorSubject()
    }

    private fun configUi() {
        reposAdapter = ReposAdapter(arrayListOf())
        reposAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        rv_repos.addItemDecoration(
            ItemVerticalSpacingDecorator(
                resources.getDimensionPixelSize(
                    R.dimen.cards_vertical_margin
                )
            )
        )
        rv_repos.adapter = reposAdapter

        srf_repos.setOnRefreshListener {
            viewModel.loadRepos(1, 40)
        }

        rv_repos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount: Int = rv_repos.layoutManager!!.childCount
                val totalItemCount: Int = rv_repos.layoutManager!!.itemCount
                val firstVisibleItemPosition: Int =
                    (rv_repos.layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                val lastVisibleItem = firstVisibleItemPosition + visibleItemCount

                // if current page has less items than PER_PAGE_DEFAULT then we should not ask
                // for the next pag from backend as there are none pages left to load.
                val currentPage: Int = reposAdapter.itemCount / PER_PAGE_DEFAULT
                val hasNext =
                    totalItemCount == PER_PAGE_DEFAULT * if (currentPage == 0) 1 else currentPage
                if (lastVisibleItem > totalItemCount - LIST_LOAD_THRESHOLD && hasNext) {
                    val nextPage = currentPage + 1
                    viewModel.loadRepos(nextPage, PER_PAGE_DEFAULT)
                }
            }
        })
    }

    private fun observeRefreshSubject() {
        disposables.add(viewModel.refreshSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { srf_repos.isRefreshing = it == Progress.SHOW })
    }

    private fun observeErrorSubject() {
        disposables.add(viewModel.errorSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
    }

    private fun observeCardClickSubject() {
        disposables.add(reposAdapter.clickSubject.subscribe {
            findNavController().navigate(
                ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment(
                    it
                )
            )
        })
    }
}
