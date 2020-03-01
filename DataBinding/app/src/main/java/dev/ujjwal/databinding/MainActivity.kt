package dev.ujjwal.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dev.ujjwal.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

//        activityMainBinding.tvName.text = "Ujjwal Maity"
//        activityMainBinding.tvEmail.text = "ujjwalmaity98@gmail.com"

        activityMainBinding.contact = Contact("Captain Marvel", "caption@gmail.com")

        activityMainBinding.handler = EventHandler(this)

        activityMainBinding.imageUrl = "https://i.redd.it/lhw4vp5yoy121.jpg"
    }
}
