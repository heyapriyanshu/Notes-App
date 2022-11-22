package com.project.notesapp.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.notesapp.Model.Notes
import com.project.notesapp.R
import com.project.notesapp.UI.Fragments.HomeFragmentDirections
import com.project.notesapp.databinding.FragmentCreateNoteBinding
import com.project.notesapp.databinding.ItemNotesBinding

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>):
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(var binding: ItemNotesBinding):RecyclerView.ViewHolder(binding.root){
    }

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.title.text=data.title
        holder.binding.notes.text=data.notes
        holder.binding.date.text=data.date

        when(data.prority){
            "1" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragment2ToEditNotesFragment2(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return notesList.size
    }
}