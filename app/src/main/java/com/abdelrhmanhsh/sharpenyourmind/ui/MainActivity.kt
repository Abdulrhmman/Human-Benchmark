package com.abdelrhmanhsh.sharpenyourmind.ui

import android.os.Bundle
import com.abdelrhmanhsh.sharpenyourmind.R
import com.abdelrhmanhsh.sharpenyourmind.viewmodels.ViewModelProviderFactory
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