package com.example.todoapp.adapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import kotlinx.android.synthetic.main.todo_items.view.*

class TodoAdapter(
    private val todosList: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_items,
                parent,
                false
            )
        )
    }

    //here is the main logic
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentTodo = todosList[position]
        holder.itemView.apply {
            todoItemTextView.text = currentTodo.title //set the text
            checkBox.isChecked = currentTodo.isChecked //set is checked
            Log.d("OnBind",currentTodo.title)
            strikeThrough(todoItemTextView, currentTodo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                strikeThrough(todoItemTextView, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    private fun strikeThrough(todoItemTextView: TextView, isChecked: Boolean) {
        if (isChecked) {
            todoItemTextView.paintFlags = todoItemTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            todoItemTextView.paintFlags =
                todoItemTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todosList.add(todo)
        notifyItemInserted(todosList.size - 1 )
    }

    fun deleteTodos() {
        todosList.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todosList.size
    }
}