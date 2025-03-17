package com.example.todolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.ToDoListTheme

@Composable
fun ScaffoldToDo(){

    Scaffold(
        topBar = { AppBar() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Appliquer le padding fourni par Scaffold !
            ) {
               Body(modifier = Modifier)
            }
        }
        
    )

}

@Preview(showBackground = true)
@Composable
fun ScaffoldToDoPreview() {
    ToDoListTheme {
        ScaffoldToDo()
    }
}