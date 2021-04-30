package com.abdelrahmman.humanbenchmark.ui

import android.os.Bundle
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}