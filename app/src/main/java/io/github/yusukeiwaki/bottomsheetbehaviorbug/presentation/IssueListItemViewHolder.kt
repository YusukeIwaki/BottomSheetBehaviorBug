package io.github.yusukeiwaki.bottomsheetbehaviorbug.presentation

import io.github.yusukeiwaki.bottomsheetbehaviorbug.databinding.ListItemIssueBinding
import io.github.yusukeiwaki.bottomsheetbehaviorbug.model.Issue

class IssueListItemViewHolder(private val binding: ListItemIssueBinding) : ArrayListItemViewHolder<Issue>(binding.root) {

    override fun bind(issue: Issue) {
        binding.issue = issue
    }
}
