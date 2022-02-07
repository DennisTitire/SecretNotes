package com.example.secretnotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.secretnotes.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM userTable WHERE userName =:userName and userPassword = :userPassword")
    fun getUser(userName: String, userPassword: String): User

//    @Update
//    suspend fun updateUser()

}