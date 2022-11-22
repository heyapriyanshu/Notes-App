package com.project.notesapp.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.notesapp.Model.Notes

@Dao
interface NotesDAO {

    @Query("SELECT * FROM NOTES ORDER BY ID ASC")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Query("DELETE FROM NOTES")
    fun deleteAll()

    @Update
    fun updateNotes(notes: Notes)

    @Query("SELECT * FROM NOTES ORDER BY prority desc")
    fun sortByHigh() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES ORDER BY date asc")
    fun sortByNewDate() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES ORDER BY date desc")
    fun sortByOldDate() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES ORDER BY prority asc")
    fun sortByLow() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES WHERE prority=1")
    fun getLowNotes() :LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES WHERE prority=2")
    fun getMediumNotes() :LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES WHERE prority=3")
    fun getHighNotes() :LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE title LIKE :searchQuery OR NOTES LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Notes>>
}