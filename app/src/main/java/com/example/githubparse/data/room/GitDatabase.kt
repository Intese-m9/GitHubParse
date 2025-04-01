package com.example.githubparse.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [GitUserDBO::class], version = 1)
abstract class GitDatabase: RoomDatabase() {
    abstract fun GitDao(): GitDao//получение объектов
}
