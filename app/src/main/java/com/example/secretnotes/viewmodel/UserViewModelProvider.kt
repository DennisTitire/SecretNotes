package com.example.secretnotes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.secretnotes.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class UserViewModelProvider(private val application: Application, private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(application, userRepository) as T
    }
}