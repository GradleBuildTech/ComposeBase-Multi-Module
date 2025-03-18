package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.dao.CourseDao
import com.example.room.dao.TutorDao
import com.example.room.entities.RoomCourseEntity
import com.example.room.entities.RoomTutorEntity

@Database(
    entities = [RoomCourseEntity::class, RoomTutorEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao

    abstract fun tutorDao(): TutorDao
}