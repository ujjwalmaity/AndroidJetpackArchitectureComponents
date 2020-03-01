package dev.ujjwal.viewmodellivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val data = MainActivityViewModel()
//        val randomNumber = data.getNumber()

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val randomNumber = model.getNumber()

//        Log.i(TAG, "Set number")
//        textView.text = randomNumber.value

        randomNumber.observe(this, Observer<String> {
            Log.i(TAG, "Set number")
            textView.text = it
        })

        button.setOnClickListener {
            model.createNumber()
        }
    }
}
