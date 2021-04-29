package com.abdelrahmman.humanbenchmark.di

import android.app.Application
import androidx.room.Room
import com.abdelrahmman.humanbenchmark.data.AppDatabase
import com.abdelrahmman.humanbenchmark.data.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}