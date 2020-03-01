package dev.ujjwal.viewmodellivedata

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityDataGenerator : ViewModel() {

    companion object {
        private val TAG: String = MainActivityDataGenerator::class.java.simpleName
    }

    private lateinit var randomNumber: String

    fun getNumber(): String {
        Log.i(TAG, "Get number")

        if (!::randomNumber.isInitialized) {
            this.createNumber()
        }
        return randomNumber
    }

    private fun createNumber() {
        Log.i(TAG, "Create number")

        val random = Random()
        randomNumber = "Number: " + (random.nextInt(500 - 1) + 1)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel destroyed")
    }
}