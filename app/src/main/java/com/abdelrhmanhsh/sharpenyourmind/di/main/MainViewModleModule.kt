package com.abdelrhmanhsh.sharpenyourmind.di.main

import androidx.lifecycle.ViewModel
import com.abdelrhmanhsh.sharpenyourmind.di.ViewModelKey
import com.abdelrhmanhsh.sharpenyourmind.viewmodels.ScoresViewModel
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