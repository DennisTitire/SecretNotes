package com.example.secretnotes.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.secretnotes.R
import com.example.secretnotes.database.UserDatabase
import com.example.secretnotes.databinding.FragmentSignUpBinding
import com.example.secretnotes.model.User
import com.example.secretnotes.repository.UserRepository
import com.example.secretnotes.viewmodel.UserViewModel
import com.example.secretnotes.viewmodel.UserViewModelProvider

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val viewBinding = binding.root

        val userRepository = UserRepository(UserDatabase(requireContext()))
        val userViewModelProvider = UserViewModelProvider(Application(), userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProvider)[UserViewModel::class.java]

        binding.cancelSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.saveUserSignUpButton.setOnClickListener {
            saveUserToDatabase()
        }

        return viewBinding
    }

    private fun saveUserToDatabase() {
        // 1. Check every EditText for existing and valid
        // 2. Check if tha password are correct
        // 3. Sava the user in the database
        val userName = binding.userNameSignUp.text.toString()
        val password = binding.userPasswordSignUp.text.toString()
        val reenterPassword = binding.reenterUserPasswordSignUp.text.toString()
        if (validateFieldsSignUp(userName, password, reenterPassword)) {
            binding.progressBar.visibility = View.VISIBLE
//            val notes = Notes("Acesta este un titlu!", "aceasta este o decriere :)")
            val user = User(userName = userName, userPassword = password)
            userViewModel.addUser(user)
            Toast.makeText(context, "Saved successfully with ${user.userName}!", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.INVISIBLE
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

    }

    private fun validateFieldsSignUp(userName: String, password: String, reenterPassword: String): Boolean {
        when {
            userName.isEmpty() -> {
                binding.userNameSignUp.error = "Please make sure to fill out Username field!"
            }
            userName.length < 4 -> {
                binding.userNameSignUp.error = "Please make sure to have more than 4 characters!"
            }
            password.isEmpty() -> {
                binding.userPasswordSignUp.error = "Please make sure to fill out Password field!"
            }
            password.length < 4 -> {
                binding.userPasswordSignUp.error =
                    "Please make sure to have more than 4 characters!"
            }
            reenterPassword.isEmpty() -> {
                binding.reenterUserPasswordSignUp.error =
                    "Please make sure to fill out ReenterPassword field!"
            }
            reenterPassword != password -> {
                binding.reenterUserPasswordSignUp.error =
                    "Please make sure that passwords are the same!"
            }
            else -> {
                return true
            }
        }
        return false
    }


}