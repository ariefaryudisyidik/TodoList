package id.co.todolist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.co.todolist.data.remote.request.LoginRequest
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.data.remote.response.AuthResponse
import id.co.todolist.data.remote.retrofit.ApiService
import id.co.todolist.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class UserRepository(
    private val apiService: ApiService
) {
    fun login(user: LoginRequest): LiveData<Resource<AuthResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiService.login(user)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.toString()))
        } catch (e: IOException) {
            emit(Resource.Error(e.toString()))
        }
    }

    fun register(user: RegisterRequest): LiveData<Resource<AuthResponse>> = liveData {
        emit(Resource.Loading())
        try {
            val response = apiService.register(user)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.toString()))
        } catch (e: IOException) {
            emit(Resource.Error(e.toString()))
        }
    }
}