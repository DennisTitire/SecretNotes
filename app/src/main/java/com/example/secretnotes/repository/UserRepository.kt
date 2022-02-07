package com.example.secretnotes.repository

import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.model.User

class UserRepository(private val db: UserDatabase) {

    suspend fun addUser(user: User) {
        db.getUserDao().addUser(user)
    }

    fun getUser(userName: String, userPassword: String): User {
        return db.getUserDao().getUser(userName, userPassword)
    }

}