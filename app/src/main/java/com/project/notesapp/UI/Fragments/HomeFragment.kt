package com.project.notesapp.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import com.project.notesapp.Model.Notes
import com.project.notesapp.R
import com.project.notesapp.UI.Adapter.NotesAdapter
import com.project.notesapp.UI.Adapter.SwipeToDelete
import com.project.notesapp.ViewModel.NotesViewModel
import com.project.notesapp.databinding.FragmentHomeBinding
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding //floating button
  //  private val adapter: NotesAdapter by lazy { NotesAdapter() }
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //displaying no data


        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

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
        if(item.itemId==R.id.menu_delete_all){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete?")
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

    }

}