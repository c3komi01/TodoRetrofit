package com.example.myprojecttodoktor.network

import com.example.myprojecttodoktor.models.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface TodoClient {

    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object {
        private var instance: TodoClient? = null

        fun getInstance(): TodoClient {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TodoClient::class.java)
            }
            return instance!!
        }
    }
}
