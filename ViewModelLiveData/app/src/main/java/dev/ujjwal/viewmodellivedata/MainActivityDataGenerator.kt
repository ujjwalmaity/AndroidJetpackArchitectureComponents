package dev.ujjwal.viewmodellivedata

import android.util.Log
import java.util.*

class MainActivityDataGenerator {

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
}