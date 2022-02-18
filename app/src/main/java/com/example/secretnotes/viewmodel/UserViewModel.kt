package com.example.secretnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretnotes.database.UserWithNotes
import com.example.secretnotes.model.Notes
import com.example.secretnotes.model.User
import com.example.secretnotes.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application, private val userRepository: UserRepository) : AndroidViewModel(application) {

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }

    fun getUser(userName: String, userPassword: String): User {
        return userRepository.getUser(userName, userPassword)
    }

    suspend fun getUserId(id: Int?): User? {
        return userRepository.getUserId(id)
    }

    suspend fun getAllUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    fun insertNotes(notes: Notes) {
        viewModelScope.launch {
            userRepository.insertNotes(notes)
        }
    }

    suspend fun getAllNotes(): List<Notes> {
        return userRepository.getAllNotes()
    }

    suspend fun getUserWithNotes(userId: Int?): List<UserWithNotes> {
        return userRepository.getUserWithNotes(userId)
    }

}