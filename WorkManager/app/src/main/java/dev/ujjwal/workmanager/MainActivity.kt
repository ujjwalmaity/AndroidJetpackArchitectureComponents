package dev.ujjwal.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MESSAGE_STATUS = "message_status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManager = WorkManager.getInstance()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val inputData = Data.Builder()
            .putString(MESSAGE_STATUS, "Message Send Successfully")
            .build()
        val request = OneTimeWorkRequest.Builder(SendWorker::class.java)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()
        btnSend.setOnClickListener {
            workManager.enqueue(request)
        }

        workManager.getWorkInfoByIdLiveData(request.id).observe(this, Observer { workInfo ->
            workInfo?.let {
                if (it.state.isFinished) {
                    val data = it.outputData
                    val dataString = data.getString(SendWorker.WORK_RESULT)
                    tvStatus.append("\n$dataString")
                }
                val state = it.state
                tvStatus.append("\n" + state.toString())
            }
        })
    }
}
