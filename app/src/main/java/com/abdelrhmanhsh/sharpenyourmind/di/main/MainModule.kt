package com.abdelrhmanhsh.sharpenyourmind.di.main

import com.abdelrhmanhsh.sharpenyourmind.data.AppDatabase
import com.abdelrhmanhsh.sharpenyourmind.data.ScoresDao
import com.abdelrhmanhsh.sharpenyourmind.data.ScoresRepository
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