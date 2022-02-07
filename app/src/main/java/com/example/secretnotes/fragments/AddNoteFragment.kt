package com.example.secretnotes.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.secretnotes.R
import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.databinding.FragmentAddNoteBinding
import com.example.secretnotes.repository.UserRepository
import com.example.secretnotes.viewmodel.UserViewModel
import com.example.secretnotes.viewmodel.UserViewModelProvider

class AddNoteFragment : Fragment() {

    private lateinit var binding : FragmentAddNoteBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        val viewBinding = binding.root

        val userRepository = UserRepository(UserDatabase(requireContext()))
        val userViewModelProvider = UserViewModelProvider(Application(), userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProvider)[UserViewModel::class.java]

        binding.saveNoteTitleButton.setOnClickListener {
            val titleNote = binding.addTitleNote.text.toString()
            // TODO: For getting and make a title note I need a @update !!

        }

        return viewBinding
    }

}