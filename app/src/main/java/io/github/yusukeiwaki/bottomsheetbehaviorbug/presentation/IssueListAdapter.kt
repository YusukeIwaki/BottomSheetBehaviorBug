package io.github.yusukeiwaki.bottomsheetbehaviorbug.presentation

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.yusukeiwaki.bottomsheetbehaviorbug.R
import io.github.yusukeiwaki.bottomsheetbehaviorbug.databinding.ListItemIssueBinding

import io.github.yusukeiwaki.bottomsheetbehaviorbug.model.Issue

class IssueListAdapter(private val context: Context) : ArrayListAdapter<Issue, IssueListItemViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListItemViewHolder? {
        val binding = DataBindingUtil.inflate<ListItemIssueBinding>(inflater, R.layout.list_item_issue, parent, false)
        return IssueListItemViewHolder(binding)
    }
}
