package com.project.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.notesapp.Model.Notes
import com.project.notesapp.Repository.NotesRepository
import com.project.notesapp.database.NotesDatabase

class NotesViewModel(applicaton: Application): AndroidViewModel(applicaton) {
    val repository: NotesRepository
//    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(true)
//    fun checkEmptyDB(notesData: List<Notes>){
//        emptyDatabase.value=notesData.isEmpty()
//
//    }
    init{
        var dao=NotesDatabase.getDatabaseInstance(applicaton).myNotesDao()
        repository=NotesRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes() : LiveData<List<Notes>> = repository.getAllNotes()
    fun getHighNotes() : LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()
    fun sortByHigh() : LiveData<List<Notes>> = repository.sortByPriorityHigh()
    fun sortByLow() : LiveData<List<Notes>> = repository.sortByPriorityLow()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }
    fun deleteAllNotes(){
        repository.deleteAll()
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}