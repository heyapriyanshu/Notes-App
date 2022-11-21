package com.project.notesapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.notesapp.R
import com.project.notesapp.UI.Adapter.NotesAdapter
import com.project.notesapp.ViewModel.NotesViewModel
import com.project.notesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding //floating button

    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)


        }

        //navigating from home fragment to create fragment

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNoteFragment2)
        }
        //set menu
        setHasOptionsMenu(true)
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu,menu)

    }

}