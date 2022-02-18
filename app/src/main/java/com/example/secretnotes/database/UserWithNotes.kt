package com.example.secretnotes.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.secretnotes.model.Notes
import com.example.secretnotes.model.User

@Entity
data class UserWithNotes(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val listNotesUser: List<Notes>
)