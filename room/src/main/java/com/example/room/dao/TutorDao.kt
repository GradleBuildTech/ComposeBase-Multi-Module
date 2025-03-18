package com.example.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.entities.RoomTutorEntity

@Dao
interface TutorDao {
    @Query("SELECT * FROM tutor")
    suspend fun getAll(): List<RoomTutorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tutors: List<RoomTutorEntity>)
}