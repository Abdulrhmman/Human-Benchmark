package com.abdelrhmanhsh.sharpenyourmind.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdelrhmanhsh.sharpenyourmind.R
import com.abdelrhmanhsh.sharpenyourmind.adapter.ScoresListAdapter
import com.abdelrhmanhsh.sharpenyourmind.data.Score
import com.abdelrhmanhsh.sharpenyourmind.util.TopSpacingItemDecoration
import com.google.android.material.snackbar.Snackbar

class ShowScoresFragment: BaseMainFragment() {

    private lateinit var recyclerAdapter: ScoresListAdapter
    private lateinit var recyclerview: RecyclerView
    private lateinit var btnDeleteAll: AppCompatButton
    private lateinit var backImg : ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_show_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.recyclerview)
        btnDeleteAll = view.findViewById(R.id.btn_delete_all)
        backImg = view.findViewById(R.id.back_btn)

        subscribeObservers()
        initRecyclerView()
        handleBtnVisibility()

        btnDeleteAll.setOnClickListener {
            handleBtnDeleteAll()
        }

        backImg.setOnClickListener {
            navController.navigate(R.id.action_showScoresFragment_to_mainFragment)
        }

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

        swipeToDelete()

    }

    private fun swipeToDelete(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val deleted: Score = recyclerAdapter.getScoreAt(viewHolder.bindingAdapterPosition)

                viewModel.delete(recyclerAdapter.getScoreAt(viewHolder.bindingAdapterPosition))

                Snackbar.make(recyclerview, R.string.score_deleted, Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo) {
                        viewModel.insert(deleted)
                    }.show()

            }
        }).attachToRecyclerView(recyclerview)

    }

    private fun handleBtnVisibility(){
        Handler(Looper.getMainLooper()).postDelayed({

            if (recyclerAdapter.itemCount == 0){
                btnDeleteAll.visibility = View.GONE

            } else {
                btnDeleteAll.visibility = View.VISIBLE

            }

        }, 200)
    }

    private fun handleBtnDeleteAll(){
        AlertDialog.Builder(context)
            .setTitle(R.string.are_you_sure)
            .setMessage(R.string.delete_all_message)
            .setPositiveButton(R.string.text_yes) { _, _ ->

                viewModel.deleteAll()

            }.setNegativeButton(R.string.text_cancel) { _, _ ->
                // do nothing
            }.show()

    }

}