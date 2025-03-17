package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.dao.CourseDao
import com.example.room.entities.RoomCourseEntity

@Database(
    entities = [RoomCourseEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class AppCourseDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}