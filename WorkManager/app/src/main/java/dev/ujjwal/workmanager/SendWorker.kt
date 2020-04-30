package dev.ujjwal.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class SendWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    companion object {
        const val WORK_RESULT = "work_result"
    }

    override fun doWork(): Result {

        val data = inputData
        val dataString = data.getString(MainActivity.MESSAGE_STATUS)

        showNotification("Work Manager", dataString ?: "Message Send")

        val outputData = Data.Builder()
            .putString(WORK_RESULT, "Task Finished")
            .build()

        return Result.success(outputData)
    }

    private fun showNotification(task: String, desc: String) {

        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "message_channel"
        val channelName = "message_name"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)

        manager.notify(1, builder.build())
    }
}