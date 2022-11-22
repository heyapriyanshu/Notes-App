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
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun deleteAll(){
        dao.deleteAll()
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }
    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
    fun sortByPriorityHigh():LiveData<List<Notes>>{
        return dao.sortByHigh()
    }
    fun sortByPriorityLow():LiveData<List<Notes>>{
        return dao.sortByLow()
    }
    fun sortByNewDate():LiveData<List<Notes>>{
        return dao.sortByNewDate()
    }
    fun sortByOldDate():LiveData<List<Notes>>{
        return dao.sortByOldDate()
    }
    fun searchNotes(item:String): LiveData<List<Notes>> {
        return dao.searchDatabase(item)
    }
}