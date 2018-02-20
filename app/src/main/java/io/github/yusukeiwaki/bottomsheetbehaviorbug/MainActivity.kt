package io.github.yusukeiwaki.bottomsheetbehaviorbug

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import io.github.yusukeiwaki.bottomsheetbehaviorbug.databinding.ActivityMainBinding
import io.github.yusukeiwaki.bottomsheetbehaviorbug.presentation.IssueListAdapter
import io.github.yusukeiwaki.bottomsheetbehaviorbug.repository.IssueRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var bottomSheetHideableHelper: BottomSheetHideableHelper
    private lateinit var adapter: IssueListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize variables
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetHideableHelper = BottomSheetHideableHelper(bottomSheet)
        adapter = IssueListAdapter(this).also {
            it.setItemClickListener { issue ->
                showOrHideBottomSheet(issue.id % 2 == 0)
            }
        }

        // setup
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        bottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, newState: Int) {
                Log.d("MainActivity", "BottomSheetCallback#onStateChanged: newState=${newState}")
                bottomSheetHideableHelper.onStateChanged(newState)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        showOrHideBottomSheet(false)
        loadAndShowIssueList()
    }

    private fun loadAndShowIssueList() {
        launch(UI) {
            val issueList = async { IssueRepository.getAll() }.await()
            adapter.updateData(issueList)
            showOrHideBottomSheet(true)
        }
    }

    private fun showOrHideBottomSheet(show: Boolean) {
        if (show) {
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetHideableHelper.setHideable(false)

        } else {
            bottomSheetHideableHelper.setHideable(true)
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}
