package com.heixss.github.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.heixss.github.R
import com.heixss.github.model.remote.RepoDetails
import io.reactivex.subjects.PublishSubject

class ReposAdapter(private var repos: ArrayList<RepoDetails>) :
    RecyclerView.Adapter<RepoDetailsViewHolder>() {
    var clickSubject = PublishSubject.create<RepoDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoDetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_card, parent, false)
        return RepoDetailsViewHolder(view, clickSubject)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoDetailsViewHolder, position: Int) {
        holder.setup(repos[position])
    }

    fun updateList(repos: List<RepoDetails>) {
        val diffResult =
            DiffUtil.calculateDiff(RepoDetailsDiffUtil(this.repos, repos))
        this.repos.clear()
        this.repos.addAll(repos)
        diffResult.dispatchUpdatesTo(this)
    }
}

class RepoDetailsViewHolder(view: View, private val clickSubject: PublishSubject<RepoDetails>) :
    RecyclerView.ViewHolder(view) {

    private lateinit var repoDetails: RepoDetails
    private val tvTitle = view.findViewById<TextView>(R.id.tv_title)

    init {
        view.setOnClickListener {
            if (::repoDetails.isInitialized)
                clickSubject.onNext(repoDetails)
        }
    }

    fun setup(repoDetails: RepoDetails) {
        this.repoDetails = repoDetails
        tvTitle.text = repoDetails.name
    }
}