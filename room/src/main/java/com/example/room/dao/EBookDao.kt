package com.example.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.entities.RoomEBookEntity

@Dao
interface EBookDao {
    @Query("SELECT * FROM ebook")
    suspend fun getAll(): List<RoomEBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ebooks: List<RoomEBookEntity>)
}