package id.co.todolist.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.todolist.data.remote.request.LoginRequest
import id.co.todolist.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        splashScreen()
    }

    private fun splashScreen() {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun login(user: LoginRequest) =
        userRepository.login(user)
}