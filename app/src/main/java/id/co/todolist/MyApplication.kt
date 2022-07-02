package id.co.todolist

import android.app.Application
import id.co.todolist.di.networkModule
import id.co.todolist.di.repositoryModule
import id.co.todolist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule
                )
            )
        }
    }
}