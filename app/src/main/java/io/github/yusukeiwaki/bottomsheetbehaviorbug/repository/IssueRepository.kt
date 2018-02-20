package io.github.yusukeiwaki.bottomsheetbehaviorbug.repository

import io.github.yusukeiwaki.bottomsheetbehaviorbug.model.Issue
import kotlinx.coroutines.experimental.delay

object IssueRepository {
    suspend fun getAll(): List<Issue> {
        delay(3500)
        val array = ArrayList<Issue>()

        repeat(60) { i ->
            val issue = Issue(id = i, title = "issue ${i+1}")
            array.add(issue)
        }

        return array
    }
}
