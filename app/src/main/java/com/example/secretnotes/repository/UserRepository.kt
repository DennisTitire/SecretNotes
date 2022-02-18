package com.example.secretnotes.repository

import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.database.UserWithNotes
import com.example.secretnotes.model.Notes
import com.example.secretnotes.model.User

class UserRepository(private val db: UserDatabase) {

    suspend fun addUser(user: User) {
        db.getUserDao().addUser(user)
    }

    fun getUser(userName: String, userPassword: String): User {
        return db.getUserDao().getUser(userName, userPassword)
    }

    suspend fun getUserId(id: Int?): User? {
        return db.getUserDao().getUserId(id)
    }

    suspend fun getAllUsers(): List<User> {
       return db.getUserDao().getAllUsers()
    }

    suspend fun insertNotes(notes: Notes) {
        db.getUserDao().insertNotes(notes)
    }

    suspend fun getAllNotes(): List<Notes> {
       return db.getUserDao().getAllNotes()
    }

    suspend fun getUserWithNotes(userId: Int?): List<UserWithNotes> {
        return db.getUserDao().getUserWithNotes(userId)
    }

}