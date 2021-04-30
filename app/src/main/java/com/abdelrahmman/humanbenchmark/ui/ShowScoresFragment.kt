package com.abdelrahmman.humanbenchmark.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.adapter.ScoresListAdapter
import com.abdelrahmman.humanbenchmark.util.TopSpacingItemDecoration

class ShowScoresFragment: BaseMainFragment() {

    private lateinit var recyclerAdapter: ScoresListAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.recyclerview)

        subscribeObservers()
        initRecyclerView()

    }

    private fun subscribeObservers(){
        viewModel.allScores.observe(viewLifecycleOwner) { scores ->
            recyclerAdapter.submitList(scores)
        }

    }

    private fun initRecyclerView(){
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ShowScoresFragment.context)
            (layoutManager as LinearLayoutManager).setReverseLayout(true)
            (layoutManager as LinearLayoutManager).setStackFromEnd(true)
            val topSpacingItemDecoration = TopSpacingItemDecoration(10)
            removeItemDecoration(topSpacingItemDecoration)
            addItemDecoration(topSpacingItemDecoration)
            recyclerAdapter = ScoresListAdapter()

            adapter = recyclerAdapter

        }
    }

}