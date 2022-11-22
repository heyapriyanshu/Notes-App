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
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

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
            oldMyNotes = notesList as ArrayList<Notes>
            binding.rcvAllNotes.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            notesAdapter=NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter=notesAdapter

         //   viewModel.checkEmptyDB(notesList)

        }
//        viewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
//            showEmptyDBViews(it)
//        })
        //navigating from home fragment to create fragment

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNoteFragment2)
        }

        //setup recycler view

        binding.rcvAllNotes.itemAnimator=SlideInUpAnimator().apply {
            addDuration = 300
        }


        //set menu
        setHasOptionsMenu(true)
        return binding.root

    }



//    private fun swipeToDelete(rv:RecyclerView) {
//        val swipeToDeleteCallBack= object : SwipeToDelete(){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
//                // Delete Item
//                viewModel.deleteNotes(deletedItem)
//                adapter.notifyItemRemoved(viewHolder.adapterPosition)
//                // Restore Deleted Item
//                restoreDeletedData(viewHolder.itemView, deletedItem)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
//        itemTouchHelper.attachToRecyclerView(rv)
//
//    }
//
//    private fun restoreDeletedData(view: View, deletedItem: Notes) {
//        val snackBar = Snackbar.make(
//            view, "Deleted '${deletedItem.title}'",
//            Snackbar.LENGTH_LONG
//        )
//        snackBar.setAction("Undo") {
//            viewModel.addNotes(deletedItem)
//        }
//        snackBar.show()
//    }

//    private fun showEmptyDBViews(emptyDB:Boolean) {
//        if(emptyDB){
//            view?.
//        }
//    }

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
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
        }
    }

    private fun sortByNewDate() {
        viewModel.sortByNewDate().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
        }
    }

    private fun sortByLow() {
        viewModel.sortByLow().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
        }
    }

    private fun sortByHigh() {
        viewModel.sortByHigh().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesAdapter = NotesAdapter(requireContext(), it)
            binding.rcvAllNotes.adapter = notesAdapter
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
        if(p0 =="high" || p0 =="High"){
            viewModel.getHighNotes().observe(viewLifecycleOwner) {
                binding.rcvAllNotes.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }else if(p0 == "med" || p0 == "medium" || p0=="Med" || p0=="Medium"){
            viewModel.getMediumNotes().observe(viewLifecycleOwner) {
                binding.rcvAllNotes.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }else if(p0 == "low" || p0 == "Low" ){
            viewModel.getLowNotes().observe(viewLifecycleOwner) {
                binding.rcvAllNotes.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                notesAdapter = NotesAdapter(requireContext(), it)
                binding.rcvAllNotes.adapter = notesAdapter
            }
        }
        for(i in oldMyNotes){

            if(i.title.contains(p0!!) || i.notes.contains(p0!!) ){
                newFilteredList.add(i)
            }
            notesAdapter.filtering(newFilteredList)
        }
    }

}