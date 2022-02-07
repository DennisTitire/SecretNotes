package com.example.secretnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.secretnotes.R
import com.example.secretnotes.databinding.FragmentListNotesBinding


class ListNotesFragment : Fragment() {

    private lateinit var binding: FragmentListNotesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentListNotesBinding.inflate(inflater, container, false)
        val viewBinding = binding.root


        binding.addNotesButton.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_addNoteFragment)
        }

        return viewBinding
    }

}