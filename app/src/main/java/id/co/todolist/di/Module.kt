package id.co.todolist.di

import id.co.todolist.data.remote.retrofit.ApiConfig
import id.co.todolist.data.repository.UserRepository
import id.co.todolist.ui.login.LoginViewModel
import id.co.todolist.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}

val repositoryModule = module {
    factory { UserRepository(get()) }
}

val networkModule = module {
    single { ApiConfig.getApiService() }
}



