package com.abdelrhmanhsh.sharpenyourmind.di

import android.app.Application
import androidx.room.Room
import com.abdelrhmanhsh.sharpenyourmind.data.AppDatabase
import com.abdelrhmanhsh.sharpenyourmind.data.AppDatabase.Companion.DATABASE_NAME
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