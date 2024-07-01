package com.chopas.mynotes.repository

import androidx.lifecycle.LiveData
import com.chopas.mynotes.db.Note
import com.chopas.mynotes.db.NoteDatabase

class NoteRepository(
    private val noteDatabase: NoteDatabase
) {
    suspend fun addNote(note: Note) {
        noteDatabase.getNoteDao().addNote(note)
    }

    suspend fun getAllNotes(): List<Note> {
        return noteDatabase.getNoteDao().getAllNotes()
    }

    suspend fun updateNote(note: Note) {
        noteDatabase.getNoteDao().updateNote(note)
    }
}