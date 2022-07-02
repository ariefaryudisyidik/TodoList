package id.co.todolist.data.remote.request

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)