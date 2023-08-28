package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.data.Todo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodoAdapter(mutableListOf())
        recyclerViewTodos.adapter = adapter //set the adapter
        recyclerViewTodos.layoutManager = GridLayoutManager(this, 3)


        buttonAddTodo.setOnClickListener {
            val todoItem = editTextTodo.text.toString()
            if (todoItem.isNotEmpty()) {
                val todo = Todo(todoItem)
                adapter.addTodo(todo)
                editTextTodo.text.clear()
            }
        }
        buttonDeleteTodo.setOnClickListener {
            adapter.deleteTodos()
        }
    }
}