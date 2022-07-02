package id.co.todolist.utils

import android.content.Context
import android.widget.Toast

object Extension {

    const val TAG = "tag"

    fun Context.toast(message: String?) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}