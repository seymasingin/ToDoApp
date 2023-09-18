package com.seymasingin.recyclerviewhomework.data

import com.seymasingin.recyclerviewhomework.data.model.Todo

object TodoDatabase {
    private val todos = mutableListOf<Todo>()

    fun getTodos(): List<Todo>{
        return todos
    }
    fun addTodo(title: String, priority: String){
        val newTodo = Todo(
            id=(todos.lastOrNull()?.id ?:0 )+1,
            title=title,
            priority = priority,
        )
        todos.add(newTodo)
    }
    fun deleteTodo(todo: Todo){
        todos.remove(todo)
    }
}