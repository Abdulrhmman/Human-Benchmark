package com.abdelrahmman.humanbenchmark.di

import com.abdelrahmman.humanbenchmark.di.main.MainFragmentBuildersModule
import com.abdelrahmman.humanbenchmark.di.main.MainModule
import com.abdelrahmman.humanbenchmark.di.main.MainScope
import com.abdelrahmman.humanbenchmark.di.main.MainViewModelModule
import com.abdelrahmman.humanbenchmark.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, MainFragmentBuildersModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}