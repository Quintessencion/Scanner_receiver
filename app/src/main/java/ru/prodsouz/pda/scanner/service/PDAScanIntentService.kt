package ru.prodsouz.pda.scanner.service

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import ru.prodsouz.pda.scanner.R

class PDAScanIntentService : IntentService("PDAScanIntentService") {

    companion object {
        const val ACTION_SCAN_DATA = "ru.prodsouz.pda.scanner.action.send"
        const val SERVICE_EXTRA_DATA = "ru.prodsouz.pda.scanner.extra.scan.data"

        const val ACTION_SEND_INTENT = "ru.prodsouz.pda.scanner.barcode.intent"
        const val BARCODE_DATA = "BARCODE_DATA"

        @JvmStatic
        fun startService(context: Context, data: String) {
            val intent = Intent(context, PDAScanIntentService::class.java).apply {
                action = ACTION_SCAN_DATA
                putExtra(SERVICE_EXTRA_DATA, data)
            }
            context.startService(intent)
        }

        const val FOREGROUND_ID = 101010
    }

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_SCAN_DATA -> {
                val data = intent.getStringExtra(SERVICE_EXTRA_DATA)
                handleScanData(data)
            }
        }
    }

    private fun handleScanData(data: String) {
//        startForeground(FOREGROUND_ID, buildForegroundNotification())

        Handler(mainLooper).post {
            val intent = Intent(ACTION_SEND_INTENT).apply { putExtra(BARCODE_DATA, data) }
            sendBroadcast(intent)

//            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(
//                intent,
//                PackageManager.MATCH_DEFAULT_ONLY
//            )
//            if (activities.isNotEmpty()) {
//                startActivity(intent)
//            } else {
//                Toast.makeText(applicationContext, getString(R.string.no_intent_activity), Toast.LENGTH_SHORT).show()
//            }
//            Toast.makeText(applicationContext, data, Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildForegroundNotification(): Notification {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }

        return NotificationCompat.Builder(this, channelId)
            .setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Notification title")
            .setContentText("Notification text")
            .setTicker("Notification ticker")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "my_service"
        val channelName = "My Background Service"
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH
        )
        chan.lightColor = Color.BLUE
        chan.importance = NotificationManager.IMPORTANCE_NONE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}
