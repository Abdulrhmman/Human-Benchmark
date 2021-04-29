package com.abdelrahmman.humanbenchmark.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abdelrahmman.humanbenchmark.BaseApplication
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.viewmodels.ScoresViewModel
import com.abdelrahmman.humanbenchmark.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

//    lateinit var viewModel: ScoresViewModel

//    private val scoresViewModel: ScoresViewModel by viewModels {
//        ScoresViewModelFactory((application as ScoresApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        scoresViewModel.allScores.observe(this) { scores ->
//
//            scores.let { println(scores) }
//        }
    }

}