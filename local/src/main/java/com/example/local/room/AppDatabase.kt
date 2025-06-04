package com.example.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.room.dao.CourseDao
import com.example.local.room.dao.EBookDao
import com.example.local.room.dao.TutorDao
import com.example.local.room.entities.RoomCourseEntity
import com.example.local.room.entities.RoomEBookEntity
import com.example.local.room.entities.RoomTutorEntity

@Database(
    entities = [RoomCourseEntity::class, RoomTutorEntity::class, RoomEBookEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao

    abstract fun tutorDao(): TutorDao

    abstract fun eBookDao(): EBookDao
}