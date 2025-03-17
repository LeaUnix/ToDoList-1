package com.example.todolist.viewModel

import androidx.lifecycle.ViewModel
import com.example.todolist.model.ToDo
import com.example.todolist.ui.uiState.ToDoListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoListViewModel : ViewModel() {

    // 1. Avoir le IUState en privée

    private val _uiState = MutableStateFlow(ToDoListState())

    // 2. Reference de ce uistate

    var uiState: StateFlow<ToDoListState> = _uiState.asStateFlow()

    // 3.Methodes pour modifier les données du State



    //TextField

    fun textChanged(newValue : String){
        _uiState.update { it.copy(newValue) }
    }

    //Percent

    fun percentChanged(){
        // 1. Récuperer tous les elements

        val allItems = _uiState.value.items

        // 2. Je veux uniquement la liste filtrées de ceux qui sont true en isDone

        val onlyDone = allItems.filter { it.isDone }

        // Taille des 2

        val allInt = allItems.size
        val doneInt = onlyDone.size

        // Calculer le pourcentage

        val calc : Double = doneInt.toDouble() / allInt.toDouble()
        val percent : Int = (calc*100).toInt()

        // Update (l'afficher)

        _uiState.update { it.copy(percentDone = percent) }



    }

    //Add

    fun add(){
        if (_uiState.value.textFieldValue != ""){
            val newToDo = ToDo(_uiState.value.textFieldValue, false)
            _uiState.update { it.copy(items = it.items + newToDo, textFieldValue = "") }
        }
        percentChanged()
    }

    //Update Bool

    fun updateDone(toDo: ToDo){
        var list = convertToMutalble()
        var index = list.indexOfFirst { it == toDo }
        list[index].isDone = !list[index].isDone
        _uiState.update { it.copy(items = list) }
        percentChanged()
    }


    //Remove

    fun delete(toDo: ToDo){
    var list = convertToMutalble()
    list.remove(toDo)
        _uiState.update { it.copy(items = list) }
        percentChanged()

    }

    fun convertToMutalble() : MutableList<ToDo>{
        val list : MutableList<ToDo> = mutableListOf()
        list += _uiState.value.items
        return list
    }


}