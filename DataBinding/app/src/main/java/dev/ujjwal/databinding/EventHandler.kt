package dev.ujjwal.databinding

import android.content.Context
import android.widget.Toast

class EventHandler(private val context: Context) {

    fun onButtonClick() {
        Toast.makeText(context, "Hello!", Toast.LENGTH_SHORT).show()
    }
}