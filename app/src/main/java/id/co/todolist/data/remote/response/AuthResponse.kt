package id.co.todolist.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

    @field:SerializedName("data")
    val data: Data,
    @field:SerializedName("errorMessage")
    val errorMessage: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("statusCode")
    val statusCode: Int
)

data class Data(

    @field:SerializedName("token")
    val token: String
)
