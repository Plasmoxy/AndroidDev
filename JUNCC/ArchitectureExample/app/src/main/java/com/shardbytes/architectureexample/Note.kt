package com.shardbytes.architectureexample

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
        var title: String,
        var description: String,
        var priority: Int = 0
) {
    
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}