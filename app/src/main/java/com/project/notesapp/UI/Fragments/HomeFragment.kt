package com.project.notesapp.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.*
import com.project.notesapp.Model.Notes
import com.project.notesapp.R
import com.project.notesapp.UI.Adapter.NotesAdapter
import com.project.notesapp.ViewModel.NotesViewModel
import com.project.notesapp.databinding.FragmentHomeBinding
import com.project.notesapp.hideKeyboard


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding //floating button
  //  private val adapter: NotesAdapter by lazy { NotesAdapter() }

    val viewModel : NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var notesAdapter:NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //displaying no data


        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

            if(notesList.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            oldMyNotes = notesList as ArrayList<Notes>
            binding.rcvAllNotes.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            notesAdapter=NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter=notesAdapter
            binding.rcvAllNotes.scheduleLayoutAnimation()



        }

        //navigating from home fragment to create fragment

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNoteFragment2)
        }

        //set menu
        setHasOptionsMenu(true)
        return binding.root

        //hide keyboard
        hideKeyboard(requireActivity())

    }





    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> {
                deleteAll()
            }
            R.id.menu_priority_high -> {
                sortByHigh()
            }
            R.id.menu_priority_low -> {
                sortByLow()
            }
            R.id.menu_date_new ->{
                sortByNewDate()
            }
            R.id.menu_date_old ->{
                sortByOldDate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortByOldDate() {
        viewModel.sortByOldDate().observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
            binding.rcvAllNotes.scheduleLayoutAnimation()
        }
    }

    private fun sortByNewDate() {
        viewModel.sortByNewDate().observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
            binding.rcvAllNotes.scheduleLayoutAnimation()
        }
    }

    private fun sortByLow() {
        viewModel.sortByLow().observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
            binding.rcvAllNotes.scheduleLayoutAnimation()
        }
    }

    private fun sortByHigh() {
        viewModel.sortByHigh().observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
            binding.rcvAllNotes.scheduleLayoutAnimation()
        }
    }

    private fun deleteAll() {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to delete all the notes?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Delete selected note from database
                Toast.makeText(context, "All notes deleted", Toast.LENGTH_SHORT).show()
                viewModel.deleteAllNotes()
            }
            .setNegativeButton("No") { dialog, id ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu,menu)

        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Type to search..."
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }

        })

    }

    private fun notesFiltering(p0: String?) {
        var newFilteredList = arrayListOf<Notes>()
        when (p0) {
            "high", "High" -> {
                viewModel.getHighNotes().observe(viewLifecycleOwner) {
                    if(it.isEmpty()){
                        binding.noDataImageView.visibility = View.VISIBLE
                        binding.noDataTextView.visibility = View.VISIBLE
                    }else{
                        binding.noDataImageView.visibility = View.INVISIBLE
                        binding.noDataTextView.visibility = View.INVISIBLE
                    }
                    binding.rcvAllNotes.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    notesAdapter = NotesAdapter(requireContext(), it)
                    binding.rcvAllNotes.adapter = notesAdapter
                    binding.rcvAllNotes.scheduleLayoutAnimation()
                }
            }
            "med", "medium", "Med", "Medium" -> {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) {
                    if(it.isEmpty()){
                        binding.noDataImageView.visibility = View.VISIBLE
                        binding.noDataTextView.visibility = View.VISIBLE
                    }else{
                        binding.noDataImageView.visibility = View.INVISIBLE
                        binding.noDataTextView.visibility = View.INVISIBLE
                    }
                    binding.rcvAllNotes.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    notesAdapter = NotesAdapter(requireContext(), it)
                    binding.rcvAllNotes.adapter = notesAdapter
                    binding.rcvAllNotes.scheduleLayoutAnimation()
                }
            }
            "low", "Low" -> {
                viewModel.getLowNotes().observe(viewLifecycleOwner) {
                    if(it.isEmpty()){
                        binding.noDataImageView.visibility = View.VISIBLE
                        binding.noDataTextView.visibility = View.VISIBLE
                    }else{
                        binding.noDataImageView.visibility = View.INVISIBLE
                        binding.noDataTextView.visibility = View.INVISIBLE
                    }
                    binding.rcvAllNotes.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    notesAdapter = NotesAdapter(requireContext(), it)
                    binding.rcvAllNotes.adapter = notesAdapter
                    binding.rcvAllNotes.scheduleLayoutAnimation()
                }
            }
        }
        val query ="%$p0%"
        viewModel.searchNotes(query).observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
                binding.noDataTextView.visibility = View.VISIBLE
            }else{
                binding.noDataImageView.visibility = View.INVISIBLE
                binding.noDataTextView.visibility = View.INVISIBLE
            }
            it?.let {
                notesAdapter.filtering(it as ArrayList<Notes>)
                binding.rcvAllNotes.scheduleLayoutAnimation()
            }
        }
        //search another way
//        for(i in oldMyNotes){
//
//            if(i.title.contains(p0!!) || i.notes.contains(p0!!) ){
//                newFilteredList.add(i)
//            }
//            notesAdapter.filtering(newFilteredList)
//        }
    }

}