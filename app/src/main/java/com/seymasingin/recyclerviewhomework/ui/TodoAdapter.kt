package com.seymasingin.recyclerviewhomework.ui

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seymasingin.recyclerviewhomework.data.TodoDatabase
import com.seymasingin.recyclerviewhomework.data.model.Todo
import com.seymasingin.recyclerviewhomework.databinding.TodoCardDesignBinding

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.CardHolder>() {

    private val todoList = mutableListOf<Todo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val binding = TodoCardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardHolder(binding)

    }
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
            holder.bind(todoList[position])
    }

    inner class CardHolder(private val binding: TodoCardDesignBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(todo:Todo){
            with(binding) {
                    todoText.text=todo.title
                    todoPriorityText.text=todo.priority
                    todoCheckBox.setOnClickListener {
                        val position = adapterPosition
                        notifyItemRemoved(position)
                        val removedTodo = todoList.removeAt(position)
                        TodoDatabase.deleteTodo(removedTodo)
                    }

                    when (todo.priority) {
                        "High" -> cardView.setCardBackgroundColor(Color.RED)
                        "Medium" -> cardView.setCardBackgroundColor(Color.BLUE)
                        "Low" -> cardView.setCardBackgroundColor(Color.YELLOW)
                        else -> cardView.setCardBackgroundColor(Color.WHITE)
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(list: List<Todo>){
        todoList.clear()
        todoList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
}