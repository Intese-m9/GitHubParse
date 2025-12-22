package com.example.githubparse.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitUserDownloadEntity::class], version = 1)
abstract class GitDatabase : RoomDatabase() {
    abstract fun GitDownloadDao(): GitDownloadDao//получение объектов загруженных реп
    abstract fun GitHubListDao(): GitListDao
}
