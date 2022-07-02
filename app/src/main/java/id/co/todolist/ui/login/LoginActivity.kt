package id.co.todolist.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.co.todolist.R
import id.co.todolist.data.remote.request.LoginRequest
import id.co.todolist.databinding.ActivityLoginBinding
import id.co.todolist.ui.MainActivity
import id.co.todolist.ui.register.RegisterActivity
import id.co.todolist.utils.Extension
import id.co.todolist.utils.Extension.toast
import id.co.todolist.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value }
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login()
        navigateToRegister()
    }

    private fun login() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                val user = LoginRequest(
                    username = username,
                    password = password
                )

                if (username.isEmpty() || password.isEmpty()) {
                    toast(resources.getString(R.string.empty_field))
                } else {
                    viewModel.login(user).observe(this@LoginActivity) { result ->
                        when (result) {
                            is Resource.Loading -> {
                                showLoading(true)
                                Log.d(Extension.TAG, resources.getString(R.string.loading))
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                toast(resources.getString(R.string.register_success))
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        MainActivity::class.java
                                    )
                                )
                                finish()
                                Log.d(Extension.TAG, result.data.toString())
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                toast(result.message)
                                Log.d(Extension.TAG, result.message.toString())
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
                btnLogin.isEnabled = false
            } else {
                progressBar.visibility = View.GONE
                btnLogin.isEnabled = true
            }
        }
    }

    private fun navigateToRegister() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}