package com.chopas.mynotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopas.mynotes.db.Note
import com.chopas.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
): ViewModel() {
    val notes: MutableLiveData<List<Note>> = MutableLiveData()

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    fun getAllNotes() = viewModelScope.launch {
        notes.postValue(noteRepository.getAllNotes())
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }
}