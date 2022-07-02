package id.co.todolist.ui.register

import androidx.lifecycle.ViewModel
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.data.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun register(user: RegisterRequest) =
        userRepository.register(user)
}