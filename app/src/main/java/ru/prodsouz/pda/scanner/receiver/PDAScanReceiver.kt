package ru.prodsouz.pda.scanner.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ru.prodsouz.pda.scanner.BuildConfig
import ru.prodsouz.pda.scanner.repository.SharedPrefs

class PDAScanReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            BuildConfig.SCAN_ACTION -> {
                val code = intent.extras?.getString(BuildConfig.SCAN_EXTRA_KEY) ?: return
                SharedPrefs.sharedPreferences =
                    context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
                SharedPrefs.barCode = code
            }
            else -> return
        }

        abortBroadcast()
    }
}
