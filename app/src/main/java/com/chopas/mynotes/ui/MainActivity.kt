package com.chopas.mynotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.chopas.mynotes.R
import com.chopas.mynotes.databinding.ActivityMainBinding
import com.chopas.mynotes.db.NoteDatabase
import com.chopas.mynotes.repository.NoteRepository
import com.chopas.mynotes.viewmodel.NoteViewModel
import com.chopas.mynotes.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.newsNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        val repository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
