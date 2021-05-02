package com.abdelrahmman.humanbenchmark.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abdelrahmman.humanbenchmark.viewmodels.ScoresViewModel
import com.abdelrahmman.humanbenchmark.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseMainFragment: DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: ScoresViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this, providerFactory).get(ScoresViewModel::class.java)
        }?: throw Exception("Invalid Activity")

    }

}