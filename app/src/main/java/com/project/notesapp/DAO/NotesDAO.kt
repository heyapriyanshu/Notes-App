package com.project.notesapp.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.notesapp.Model.Notes

@Dao
interface NotesDAO {

    @Query("SELECT * FROM NOTES ORDER BY ID DESC")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

   /* @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)
*/
    @Update
    fun updateNotes(notes: Notes)
}