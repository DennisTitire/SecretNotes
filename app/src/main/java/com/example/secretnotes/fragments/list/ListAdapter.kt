package com.example.secretnotes.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secretnotes.database.UserWithNotes
import com.example.secretnotes.databinding.CustomRowBinding
import com.example.secretnotes.model.Notes

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userListNotes = listOf<UserWithNotes>()

    class ListViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            with(userListNotes[position]) {

                binding.titleNoteList.text = listNotesUser[position].noteTitle
                binding.descriptionNoteList.text = listNotesUser[position].noteDescription

//                for (number in userListNotes.indices) {
//                    binding.descriptionNoteList.text =
//                }
//                listNotesUser.forEach {
//                    binding.titleNoteList.text = it.toString()
//                }
//                listNotesUser.forEach {
//                    binding.descriptionNoteList.text = it.toString()
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userListNotes.size
    }

    fun setData(notes: List<UserWithNotes>) {
        this.userListNotes = notes
        notifyDataSetChanged()
    }

}