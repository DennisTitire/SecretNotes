package com.example.secretnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userName: String?,
    val userPassword: String?,
    val notes: Notes? = null
): Serializable