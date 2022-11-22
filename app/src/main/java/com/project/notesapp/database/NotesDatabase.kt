package com.project.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.notesapp.DAO.NotesDAO
import com.project.notesapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase:RoomDatabase(){
    abstract fun myNotesDao(): NotesDAO

    //Java-> static Kotlin-> Companion object
    companion object{
        @Volatile //easy accessible or visibile to other threads
        var INSTANCE:NotesDatabase?=null  //nullable so that we can store null value

        fun getDatabaseInstance(context: Context):NotesDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance=
                    Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                    INSTANCE=roomDatabaseInstance
                  return return roomDatabaseInstance
            }
        }
    }
}