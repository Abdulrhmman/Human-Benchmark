package com.abdelrahmman.humanbenchmark.di

import androidx.lifecycle.ViewModelProvider
import com.abdelrahmman.humanbenchmark.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}