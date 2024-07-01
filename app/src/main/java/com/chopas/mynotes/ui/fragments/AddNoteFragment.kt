package com.chopas.mynotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.chopas.mynotes.databinding.FragmentAddNoteBinding
import com.chopas.mynotes.db.Note
import com.chopas.mynotes.ui.MainActivity
import com.chopas.mynotes.viewmodel.NoteViewModel

class AddNoteFragment: Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: NoteViewModel
    private val args: AddNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val content = binding.editTextContent.text.toString().trim()

            if (title.isEmpty()) {
                binding.editTextTitle.error = "Title required"
                binding.editTextTitle.requestFocus()
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                binding.editTextContent.error = "Note required"
                binding.editTextContent.requestFocus()
                return@setOnClickListener
            }

            var note = Note(title, content)

            if (args.selectedNote == null) {
                viewModel.addNote(note)
            } else {
                args.selectedNote?.let {
                    note.id = it.id
                    viewModel.updateNote(note)
                }
            }

            val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        args.selectedNote?.let {
            binding.editTextTitle.setText(it.title)
            binding.editTextContent.setText(it.note)
        }
    }
}