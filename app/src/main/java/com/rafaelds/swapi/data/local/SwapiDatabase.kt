package com.rafaelds.swapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        const val DB_NAME = "swapidatabase"
    }
}
