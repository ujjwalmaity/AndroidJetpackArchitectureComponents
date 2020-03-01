package dev.ujjwal.databinding

import android.content.Context
import android.widget.Toast

class EventHandler(private val context: Context) {

    fun onButtonClick(name: String) {
        Toast.makeText(context, "Hello! $name", Toast.LENGTH_SHORT).show()
    }
}