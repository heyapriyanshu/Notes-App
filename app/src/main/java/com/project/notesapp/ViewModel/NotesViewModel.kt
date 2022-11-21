package com.project.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.notesapp.Model.Notes
import com.project.notesapp.Repository.NotesRepository
import com.project.notesapp.database.NotesDatabase

class NotesViewModel(applicaton: Application): AndroidViewModel(applicaton) {
    val repository: NotesRepository

    init{
        var dao=NotesDatabase.getDatabaseInstance(applicaton).myNotesDao()
        repository=NotesRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes() : LiveData<List<Notes>> = repository.getAllNotes()

   /* fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }*/
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}