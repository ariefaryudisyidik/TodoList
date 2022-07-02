package id.co.todolist.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.co.todolist.R
import id.co.todolist.data.remote.request.RegisterRequest
import id.co.todolist.databinding.ActivityRegisterBinding
import id.co.todolist.ui.login.LoginActivity
import id.co.todolist.utils.Extension.TAG
import id.co.todolist.utils.Extension.toast
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
        navigateToLogin()
    }

    private fun register() {
        binding.apply {
            btnRegister.setOnClickListener {
                val email = edtEmail.text.toString()
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                val user = RegisterRequest(
                    email = email,
                    username = username,
                    password = password
                )

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    toast(resources.getString(R.string.empty_field))
                } else {
                    viewModel.register(user).observe(this@RegisterActivity) { result ->
                        when (result) {
                            is Resource.Loading -> {
                                showLoading(true)
                                Log.d(TAG, resources.getString(R.string.loading))
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                toast(resources.getString(R.string.register_success))
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                                finish()
                                Log.d(TAG, result.data.toString())
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                toast(result.message)
                                Log.d(TAG, result.message.toString())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(visible: Boolean) {
        binding.apply {
            if (visible) {
                progressBar.visibility = View.VISIBLE
                btnRegister.isEnabled = false
            } else {
                progressBar.visibility = View.GONE
                btnRegister.isEnabled = true
            }
        }
    }

    private fun navigateToLogin() {
        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
}