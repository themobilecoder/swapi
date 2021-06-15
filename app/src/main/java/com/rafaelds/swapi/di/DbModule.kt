package com.rafaelds.swapi.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafaelds.swapi.data.local.SwapiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            SwapiDatabase::class.java,
            SwapiDatabase.DB_NAME
        ).build()
    }

}