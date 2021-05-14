package com.abdelrahmman.humanbenchmark.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.viewmodels.ScoresViewModel
import com.abdelrahmman.humanbenchmark.viewmodels.ViewModelProviderFactory
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