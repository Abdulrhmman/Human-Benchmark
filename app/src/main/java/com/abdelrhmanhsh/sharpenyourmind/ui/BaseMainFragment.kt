package com.abdelrhmanhsh.sharpenyourmind.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.abdelrhmanhsh.sharpenyourmind.viewmodels.ScoresViewModel
import com.abdelrhmanhsh.sharpenyourmind.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseMainFragment: DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: ScoresViewModel
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this, providerFactory).get(ScoresViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        navController = Navigation.findNavController(view)

    }

}