package com.example.todolist.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.model.ToDo

@Composable
fun ToDoItem(
    toDo: ToDo,
    onDoneChanged : () -> Unit,
    onDelete : () -> Unit,
){

    Row(
        modifier = Modifier
        .fillMaxWidth()
            .height(50.dp)

    ) {

        Text(toDo.title)
        Spacer(modifier = Modifier.weight(1.0F))
        Switch(
            checked = toDo.isDone,
            onCheckedChange = { onDoneChanged()},

        )
        IconButton(onClick = {onDelete() })
        {

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error)

        }


    }

}
