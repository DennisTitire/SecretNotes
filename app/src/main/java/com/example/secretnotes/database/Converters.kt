package com.example.secretnotes.database

import androidx.room.TypeConverter
import com.example.secretnotes.model.Notes

class Converters {

    @TypeConverter
    fun fromNotes(notes: Notes?): String? {
        return notes?.title
    }

    @TypeConverter
    fun toNotes(title: String?): Notes {
        return Notes(title,title)
    }


}