package com.abdelrahmman.humanbenchmark.di.main

import com.abdelrahmman.humanbenchmark.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector()
    abstract fun contributeAimTrainerFragment(): AimTrainerFragment

    @ContributesAndroidInjector()
    abstract fun contributeChimpTestFragment(): ChimpTestFragment

    @ContributesAndroidInjector()
    abstract fun contributeNumberMemoryFragment(): NumberMemoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeReactionTimeFragment(): ReactionTimeFragment

    @ContributesAndroidInjector()
    abstract fun contributeVerbalMemoryFragment(): VerbalMemoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeVisualMemoryFragment(): VisualMemoryFragment

    @ContributesAndroidInjector()
    abstract fun contributeShowScoresFragment(): ShowScoresFragment

}