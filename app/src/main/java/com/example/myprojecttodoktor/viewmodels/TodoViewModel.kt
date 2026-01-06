package com.example.myprojecttodoktor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprojecttodoktor.data.TodoRepository
import com.example.myprojecttodoktor.models.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.emptyList


data class TodoUiState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
    val errorMessage: String? = null
)

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )
            try {
                val todos = repository.getTodos()
                _uiState.value = _uiState.value.copy(
                    todos = todos,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}
