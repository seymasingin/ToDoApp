package com.seymasingin.recyclerviewhomework.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seymasingin.recyclerviewhomework.R
import com.seymasingin.recyclerviewhomework.common.viewBinding
import com.seymasingin.recyclerviewhomework.data.TodoDatabase
import com.seymasingin.recyclerviewhomework.databinding.DialogDesignBinding
import com.seymasingin.recyclerviewhomework.databinding.FragmentHomeBinding
import com.seymasingin.recyclerviewhomework.databinding.TodoCardDesignBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding:: bind)
    private val todoAdapter = TodoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            toolbarHome.title= "Todos"
            rvHome.adapter= todoAdapter
            todoAdapter.updateList(TodoDatabase.getTodos())

            fabHome.setOnClickListener {
                showAddDialog()
            }
        }
    }
    private fun showAddDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogDesignBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val dialog =builder.create()

        with (dialogBinding){

            btnAddTodo.setOnClickListener{
                    val title = etTodo.text.toString()
                    val priority = when {
                        rdBtnLow.isChecked -> "Low"
                        rdBtnMedium.isChecked -> "Medium"
                        rdBtnHigh.isChecked -> "High"
                        else -> ""
                    }
                 if(title.isNotEmpty() && priority.isNotEmpty()){
                    TodoDatabase.addTodo(title, priority)
                     todoAdapter.updateList(TodoDatabase.getTodos())
                     dialog.dismiss()
                 }
                else{
                    Toast.makeText(requireContext(), "Please fill in the blanks!", Toast.LENGTH_SHORT).show()
                 }
            }
            dialog.show()
        }
    }
}