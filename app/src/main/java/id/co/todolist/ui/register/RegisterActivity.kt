package id.co.todolist.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.databinding.ActivityRegisterBinding
import id.co.todolist.ui.login.LoginActivity
import id.co.todolist.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        register()
    }

    private fun register() {
        binding.apply {
            btnRegister.setOnClickListener {
                val email = edtEmail.text.toString()
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                val register = RegisterRequest(
                    email = email,
                    username = username,
                    password = password
                )
                viewModel.register(register).observe(this@RegisterActivity) { result ->
                    when (result) {
                        is Resource.Loading -> {
                            Log.d("Register", "Loading")
                        }
                        is Resource.Success -> {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Berhasil mendaftar! Silahkan login",
                                Toast.LENGTH_SHORT
                            ).show()
                            val direction = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(direction)
                            finish()
                        }
                        is Resource.Error -> {
                            Log.d("Register", "Error")
                            Toast.makeText(this@RegisterActivity, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}