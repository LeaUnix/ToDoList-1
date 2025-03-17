package com.example.todolist.ui.uiState

import com.example.todolist.model.ToDo

data class ToDoListState(
    val textFieldValue: String = "",
    val percentDone: Int = 100,
    val items : List<ToDo> = listOf()
)