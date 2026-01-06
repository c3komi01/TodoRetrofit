package com.example.myprojecttodoktor.data

import com.example.myprojecttodoktor.models.Todo
import com.example.myprojecttodoktor.network.TodoClient

class TodoRepository {

    private val api = TodoClient.getInstance()

    suspend fun getTodos(): List<Todo> {
        return api.getTodos()
    }
}
