package com.shardbytes.architectureexample

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.jetbrains.anko.doAsync

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    
    companion object {
        private var instance: NoteDatabase? = null
        
        @Synchronized fun getInstance(ctx: Context): NoteDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                            ctx.applicationContext,
                            NoteDatabase::class.java,
                            "note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance!!
        }
        
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                
                // populate the database
                doAsync { 
                    instance!!.noteDao().insert(Note(
                            "Boi",
                            "kysss",
                            3
                    ))

                    instance!!.noteDao().insert(Note(
                            "Sebu",
                            "asder",
                            1
                    ))
                }
            }
        }
        
    }

    abstract fun noteDao(): NoteDao // injected by Room when subclassed
}