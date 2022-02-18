package com.example.secretnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = false)
    val noteTitle: String,
    val noteDescription: String,
    val userId: Int?
)