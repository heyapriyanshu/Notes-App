package com.project.notesapp.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.project.notesapp.Model.Notes
import com.project.notesapp.R
import com.project.notesapp.ViewModel.NotesViewModel
import com.project.notesapp.databinding.FragmentCreateNoteBinding


import java.util.*


class CreateNoteFragment : Fragment() {
    lateinit var binding : FragmentCreateNoteBinding
     var priority: String="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCreateNoteBinding.inflate(layoutInflater,container,false)




        //already green
        binding.createPGreen.setImageResource(R.drawable.ic_baseline_check_24)
        //Priority Buttons
        binding.createPGreen.setOnClickListener {
            priority="1"
            binding.createPGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.createPRed.setImageResource(0)
            binding.createPYellow.setImageResource(0)

        }
        binding.createPRed.setOnClickListener{
            priority="3"
            binding.createPRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.createPGreen.setImageResource(0)
            binding.createPYellow.setImageResource(0)
        }
        binding.createPYellow.setOnClickListener{
            priority="2"
            binding.createPYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.createPGreen.setImageResource(0)
            binding.createPRed.setImageResource(0)

        }

        //set menu
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_ad){
            createNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    private fun createNotes() {
        var title = binding.createTitle.text.toString()

        var notes = binding.createNotes.text.toString()

        if (title.isEmpty() && notes.isEmpty()) {
            Toast.makeText(context, "Please Enter All The Fields...", Toast.LENGTH_SHORT).show()
        } else if (title.isEmpty()) {
            Toast.makeText(context, "Please Enter Title...", Toast.LENGTH_SHORT).show()
        } else if (notes.isEmpty()) {
            Toast.makeText(context, "Notes Can't Be Empty...", Toast.LENGTH_SHORT).show()
        } else{


        var title = binding.createTitle.text.toString()
        var notes = binding.createNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())

        val data = Notes(
            null,
            title = title,
            notes = notes,
            date = notesDate.toString(), priority
        )
        viewModel.addNotes(data)
        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_SHORT).show()

        //navigate back to home screen after completion
        findNavController().navigate(R.id.action_createNoteFragment2_to_homeFragment2)
    }

    }


}