package com.heixss.github.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.heixss.github.model.remote.RepoDetails

class RepoDetailsDiffUtil(
    private var oldCards: List<RepoDetails>,
    private var newCards: List<RepoDetails>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldCards[oldItemPosition].id == newCards[newItemPosition].id

    override fun getOldListSize(): Int = oldCards.size


    override fun getNewListSize(): Int = newCards.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldCards[oldItemPosition].name == newCards[newItemPosition].name &&
                oldCards[oldItemPosition].description == newCards[newItemPosition].description)
    }
}