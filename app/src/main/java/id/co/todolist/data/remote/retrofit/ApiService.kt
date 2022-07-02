package id.co.todolist.data.remote.retrofit

import id.co.todolist.data.remote.request.LoginRequest
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
    ): AuthResponse

    @POST("register")
    suspend fun register(
        @Body register: RegisterRequest
    ): AuthResponse

}