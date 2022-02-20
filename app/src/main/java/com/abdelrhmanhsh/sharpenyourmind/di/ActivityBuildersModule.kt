package com.abdelrhmanhsh.sharpenyourmind.di

import com.abdelrhmanhsh.sharpenyourmind.di.main.MainFragmentBuildersModule
import com.abdelrhmanhsh.sharpenyourmind.di.main.MainModule
import com.abdelrhmanhsh.sharpenyourmind.di.main.MainScope
import com.abdelrhmanhsh.sharpenyourmind.di.main.MainViewModelModule
import com.abdelrhmanhsh.sharpenyourmind.ui.MainActivity
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