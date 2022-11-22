package com.project.notesapp.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.notesapp.Model.Notes
import com.project.notesapp.R
import com.project.notesapp.ViewModel.NotesViewModel
import com.project.notesapp.databinding.FragmentEditNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {
    var priority: String="1"
    val viewModel : NotesViewModel by viewModels()
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding : FragmentEditNotesBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(oldNotes.data.title).toString()
        binding.editNotes.setText(oldNotes.data.notes).toString()

        binding.editPGreen.setImageResource(R.drawable.green_dot)
        binding.editPYellow.setImageResource(R.drawable.yellow_dot)
        binding.editPRed.setImageResource(R.drawable.red_dot)

        when(oldNotes.data.prority){
            "1" ->{
                priority= "1"
                binding.editPGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPRed.setImageResource(0)
                binding.editPYellow.setImageResource(0)
            }
            "2" ->{
                priority= "2"
                binding.editPYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPGreen.setImageResource(0)
                binding.editPRed.setImageResource(0)
            }
            "3" ->{
                priority= "3"
                binding.editPRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPGreen.setImageResource(0)
                binding.editPYellow.setImageResource(0)
            }
        }


        //set menu
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_save){
            updateNotes()
        }else if(item.itemId==R.id.update_menu_delete){
            deleteSelectedNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteSelectedNote() {


        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Delete selected note from database
                var id = oldNotes.data.id
                if (id != null) {
                    viewModel.deleteNotes(id)
                }
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editNotesFragment2_to_homeFragment2)

            }
            .setNegativeButton("No") { dialog, id ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

    }

    private fun updateNotes() {
        var title=binding.editTitle.text.toString()
        var notes=binding.editNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())

        val data = Notes(oldNotes.data.id,
            title = title,
            notes =  notes,
            date = notesDate.toString(), priority)
        viewModel.updateNotes(data)
        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        //navigate back to home screen after completion
        findNavController().navigate(R.id.action_editNotesFragment2_to_homeFragment2)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }


}