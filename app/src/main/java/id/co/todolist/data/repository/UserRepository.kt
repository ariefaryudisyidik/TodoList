package id.co.todolist.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.co.todolist.data.remote.request.LoginRequest
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.data.remote.response.AuthResponse
import id.co.todolist.data.remote.retrofit.ApiService
import id.co.todolist.utils.Resource

class UserRepository(
    private val apiService: ApiService
) {
    fun login(login: LoginRequest): LiveData<Resource<AuthResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = apiService.login(login)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            Log.e("login", "login: ${e.message.toString()}")
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun register(register: RegisterRequest): LiveData<Resource<AuthResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = apiService.register(register)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            Log.e("login", "login: ${e.message.toString()}")
            emit(Resource.Error(e.message.toString()))
        }
    }
}