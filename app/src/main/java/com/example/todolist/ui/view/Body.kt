package com.example.todolist.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.ui.theme.ToDoListTheme
import com.example.todolist.viewModel.ToDoListViewModel

@Composable
fun Body(modifier: Modifier, vm : ToDoListViewModel = viewModel()){

    //val fake = listOf<ToDo>(ToDo("Carotte", false))

    val state by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
    Surface(
        modifier = Modifier.fillMaxWidth()) {
       OutlinedTextField(
           value = state.textFieldValue ,
           onValueChange = { newValue -> vm.textChanged(newValue)},
           label = { Text("Nouveau ToDo") },
           trailingIcon = {
               IconButton(onClick = {
                    vm.add()
               }) {
                 Icon(Icons.AutoMirrored.Filled.Send,contentDescription = null)
               }
           }
       )

     }

        HorizontalDivider()
        Text("Pourcentage effectué ${state.percentDone}% :")
        HorizontalDivider()

        if (state.items.isEmpty()){
            Text("Rien pour le moment ")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.items) { ToDo ->
                    ToDoItem(
                        toDo = ToDo,
                        onDoneChanged = {vm.updateDone(toDo = ToDo)} ,
                        onDelete = {vm.delete(toDo = ToDo)} )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    ToDoListTheme {
        Body(modifier = Modifier)
    }
}
