package com.sweeteri.stepikclient.data.local
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val average: Double,
    val learnersCount: Int
)

