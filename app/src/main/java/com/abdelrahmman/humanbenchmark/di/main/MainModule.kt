package com.abdelrahmman.humanbenchmark.di.main

import com.abdelrahmman.humanbenchmark.data.AppDatabase
import com.abdelrahmman.humanbenchmark.data.ScoresDao
import com.abdelrahmman.humanbenchmark.data.ScoresRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideScoresDao(db: AppDatabase): ScoresDao {
        return db.getScoreDao()
    }

    @MainScope
    @Provides
    fun provideScoresRepository(
        scoresDao: ScoresDao
    ): ScoresRepository {
        return ScoresRepository(
            scoresDao
        )
    }

}