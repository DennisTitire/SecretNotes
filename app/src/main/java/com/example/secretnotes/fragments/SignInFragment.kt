package com.example.secretnotes.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.secretnotes.R
import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.databinding.FragmentSignInBinding
import com.example.secretnotes.repository.UserRepository
import com.example.secretnotes.viewmodel.UserViewModel
import com.example.secretnotes.viewmodel.UserViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val viewBinding = binding.root

        val userRepository = UserRepository(UserDatabase(requireContext()))
        val userViewModelProvider = UserViewModelProvider(Application(), userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProvider)[UserViewModel::class.java]

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            signIn()
        }


        return viewBinding
    }

    private fun signIn() {
        val userName = binding.userNameSignIn.text.toString()
        val userPassword = binding.userPasswordSignIn.text.toString()
        if (validateFieldsSignIn(userName, userPassword)) {
            lifecycleScope.launch(Dispatchers.IO) {
                val user = userViewModel.getUser(userName, userPassword)
                if (user != null) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        findNavController().navigate(R.id.action_signInFragment_to_listNotesFragment)
                        Toast.makeText(context, "Successfully Sign in!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "The user don't exist or credentials are incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun validateFieldsSignIn(userName: String, password: String): Boolean {
        when {
            userName.isEmpty() -> {
                binding.userNameSignIn.error = "Please make sure to fill out Username field!"
            }
            userName.length < 4 -> {
                binding.userNameSignIn.error = "Please make sure to have more than 4 characters!"
            }
            password.isEmpty() -> {
                binding.userPasswordSignIn.error = "Please make sure to fill out Password field!"
            }
            password.length < 4 -> {
                binding.userPasswordSignIn.error = "Please make sure to have more than 4 characters!"
            }
            else -> {
                return true
            }
        }
        return false
    }


}