package com.example.secretnotes.database

import androidx.room.*
import com.example.secretnotes.model.Notes
import com.example.secretnotes.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM userTable WHERE userName =:userName and userPassword = :userPassword")
    fun getUser(userName: String, userPassword: String): User

    @Query("SELECT * FROM userTable WHERE userId=:id ")
    suspend fun getUserId(id: Int?): User?

    @Query("SELECT * FROM userTable")
    suspend fun getAllUsers(): List<User>

    // one to many relation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Notes>

    @Transaction
    @Query("SELECT * FROM userTable WHERE userId =:userId")
    suspend fun getUserWithNotes(userId: Int?): List<UserWithNotes>

}