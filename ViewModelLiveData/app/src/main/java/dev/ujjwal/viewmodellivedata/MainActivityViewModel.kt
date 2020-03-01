package dev.ujjwal.viewmodellivedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

    companion object {
        private val TAG: String = MainActivityViewModel::class.java.simpleName
    }

    private lateinit var randomNumber: MutableLiveData<String>

    fun getNumber(): MutableLiveData<String> {
        Log.i(TAG, "Get number")

        if (!::randomNumber.isInitialized) {
            randomNumber = MutableLiveData()
            this.createNumber()
        }
        return randomNumber
    }

    fun createNumber() {
        Log.i(TAG, "Create number")

        val random = Random()
        randomNumber.value = "Number: " + (random.nextInt(500 - 1) + 1)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel destroyed")
    }
}