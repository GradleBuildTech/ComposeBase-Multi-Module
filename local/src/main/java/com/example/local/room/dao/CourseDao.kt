package com.example.local.room.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.room.entities.RoomCourseEntity

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    suspend fun getAll(): List<RoomCourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<RoomCourseEntity>)
}