package com.example.secretnotes.fragments.list

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secretnotes.R
import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.database.UserWithNotes
import com.example.secretnotes.databinding.FragmentListNotesBinding
import com.example.secretnotes.fragments.SignInFragment
import com.example.secretnotes.fragments.SignInFragment.Companion.getUserId
import com.example.secretnotes.model.Notes
import com.example.secretnotes.repository.UserRepository
import com.example.secretnotes.viewmodel.UserViewModel
import com.example.secretnotes.viewmodel.UserViewModelProvider
import kotlinx.coroutines.launch


class ListNotesFragment : Fragment() {

    private val TAG = "List"
    private lateinit var binding: FragmentListNotesBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentListNotesBinding.inflate(inflater, container, false)
        val viewBinding = binding.root

        val userRepository = UserRepository(UserDatabase(requireContext()))
        val userViewModelProvider = UserViewModelProvider(Application(), userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProvider)[UserViewModel::class.java]

        recyclerViewSetup()
        showAllUsers()


        binding.addNotesButton.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_addNoteFragment)
        }

        return viewBinding
    }

    private fun showAllUsers() {
        lifecycleScope.launch {
            val users = userViewModel.getAllUsers()
            Log.d(TAG, "$users")
        }
    }

    private fun recyclerViewSetup() {
        var notes: List<UserWithNotes>
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            notes = userViewModel.getUserWithNotes(getUserId)
            adapter.setData(notes)
        }
    }

}