package com.project.notesapp.Repository

import androidx.lifecycle.LiveData
import com.project.notesapp.DAO.NotesDAO
import com.project.notesapp.Model.Notes


class NotesRepository(val dao: NotesDAO) {
    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun insertNotes(notes : Notes){
        dao.insertNotes(notes)
    }
   /* fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }*/
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}