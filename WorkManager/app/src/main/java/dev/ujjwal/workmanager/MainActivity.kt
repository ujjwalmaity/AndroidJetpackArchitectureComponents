package dev.ujjwal.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManager = WorkManager.getInstance()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val request = OneTimeWorkRequest.Builder(SendWorker::class.java)
            .setConstraints(constraints)
            .build()
        btnSend.setOnClickListener {
            workManager.enqueue(request)
        }

        workManager.getWorkInfoByIdLiveData(request.id).observe(this, Observer { workInfo ->
            workInfo?.let {
                val state = it.state
                tvStatus.append("\n" + state.toString())
            }
        })
    }
}
