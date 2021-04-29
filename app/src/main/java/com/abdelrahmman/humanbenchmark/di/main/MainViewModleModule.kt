package com.abdelrahmman.humanbenchmark.di.main

import androidx.lifecycle.ViewModel
import com.abdelrahmman.humanbenchmark.di.ViewModelKey
import com.abdelrahmman.humanbenchmark.viewmodels.ScoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ScoresViewModel::class)
    abstract fun bindScoresViewModel(scoresViewModel: ScoresViewModel): ViewModel


}