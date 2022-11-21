package com.project.notesapp.UI.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.project.notesapp.R
import com.project.notesapp.databinding.FragmentEditNotesBinding

class EditNotesFragment : Fragment() {

    val notes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding : FragmentEditNotesBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(notes.data.title).toString()
        binding.editNotes.setText(notes.data.notes).toString()

        binding.editPGreen.setImageResource(R.drawable.green_dot)
        binding.editPYellow.setImageResource(R.drawable.yellow_dot)
        binding.editPRed.setImageResource(R.drawable.red_dot)

        when(notes.data.prority){
            "1" ->{
                binding.editPGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPRed.setImageResource(0)
                binding.editPYellow.setImageResource(0)
            }
            "2" ->{
                binding.editPYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPGreen.setImageResource(0)
                binding.editPRed.setImageResource(0)
            }
            "3" ->{
                binding.editPRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.editPGreen.setImageResource(0)
                binding.editPYellow.setImageResource(0)
            }
        }


        //set menu
        setHasOptionsMenu(true)


        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }


}