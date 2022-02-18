package com.example.secretnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    val userName: String? = null,
    val userPassword: String? = null
)