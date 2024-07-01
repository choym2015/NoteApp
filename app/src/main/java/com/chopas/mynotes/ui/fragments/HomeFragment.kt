package com.chopas.mynotes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.chopas.mynotes.adapters.NoteAdapter
import com.chopas.mynotes.databinding.FragmentHomeBinding
import com.chopas.mynotes.ui.MainActivity
import com.chopas.mynotes.viewmodel.NoteViewModel

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        binding.buttonAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = (activity as MainActivity).viewModel

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            noteAdapter.differ.submitList(it)
        })

        viewModel.getAllNotes()
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter()
        noteAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            action.selectedNote = it
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.recyclerView.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}